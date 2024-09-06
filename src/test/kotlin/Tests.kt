import org.json.JSONException
import org.junit.jupiter.api.assertDoesNotThrow
import tgm.mrafeiner.JSONPersistence
import tgm.mrafeiner.Model
import tgm.mrafeiner.Entry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class Tests {
    /**
     * Tests if the JSONPersistence class can save and load a model correctly
     * by comparing the saved model with the loaded model
     */
    @Test
    fun JSONPersistenceTestSaveLoad(){
        val jsonPersistence = JSONPersistence()
        val model = Model(listOf(Entry("a", "b")), 1, 2)
        jsonPersistence.save(model)
        val loadedModel = jsonPersistence.load()
        assertEquals(model, loadedModel)
    }

    /**
     * Tests if the JSONPersistence class will save and load a model correctly
     * by comparing the saved model with a different model
     */
    @Test
    fun JSONPersistenceTestSaveLoadDifferentModel(){
        val jsonPersistence = JSONPersistence()
        var model = Model(listOf(Entry("a", "b")), 1, 2)
        jsonPersistence.save(model)
        val loadedModel = jsonPersistence.load()
        model = Model(listOf(Entry("c", "d")), 3, 4)
        assertNotEquals(model, loadedModel)
    }

    /**
     * Tests if the JSONPersistence class will throw an exception when trying to load a non-existing file
     */
    @Test
    fun JSONPersistenceTestNoFileSaved(){
        val jsonPersistence = JSONPersistence()
        assertDoesNotThrow {
            jsonPersistence.load()
        }
    }

    /**
     * Tests if the JSONPersistence class will return a default model when trying to load a non-existing file
     */
    @Test
    fun JSONPersistenceTestDefaultsNoFileSaved(){
        val jsonPersistence = JSONPersistence()
        // remove the save.json file
        assertDoesNotThrow {
            java.io.File("save.json").delete()
        }
        val model = jsonPersistence.load()
        assertEquals(model, Model(listOf(Entry("https://www.google.com", "Google")), 0, 0))
    }
}