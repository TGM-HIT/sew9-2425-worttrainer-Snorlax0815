import org.json.JSONException
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito
import tgm.mrafeiner.JSONPersistence
import tgm.mrafeiner.Model
import tgm.mrafeiner.Entry
import tgm.mrafeiner.Worttrainer
import javax.swing.JOptionPane
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

    /**
     * Tests if the displayEntry function of the Worttrainer class will return the correct value
     * when the user enters the correct value. Also checks if the model data is updated correctly.
     */
    @Test
    fun displayEntryTestStandard(){
        val wt = Worttrainer(JSONPersistence())
        val entry = Entry("https://cdn.pixabay.com/photo/2017/02/14/03/03/ama-dablam-2064522_1280.jpg", "Mountain")
        // mock the behavior of JOptionPane
        // every time JOptionPane.showInputDialog is called, it will instead just return "Mountain"
        Mockito.mockStatic(JOptionPane::class.java).use { mockedJOptionPane ->
            mockedJOptionPane.`when`<String> {
                JOptionPane.showInputDialog(
                    Mockito.any(),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyInt(),
                    Mockito.any(),
                    Mockito.any(),
                    Mockito.any()
                )
            }.thenReturn("Mountain")
            assertEquals("Mountain", wt.displayEntry(entry))
            // also check the other model data
            assertEquals(1, wt.getModel().countCorrect)
            assertEquals(0, wt.getModel().countFalse)
            assertEquals(true, wt.getPrevious())
        }
    }

    /**
     * Tests if the displayEntry function of the Worttrainer class will return the correct value
     * when the user enters the wrong value. Also checks if the model data is updated correctly.
     */
    @Test
    fun displayEntryTestWrong(){
        val wt = Worttrainer(JSONPersistence())
        val entry = Entry("https://cdn.pixabay.com/photo/2017/02/14/03/03/ama-dablam-2064522_1280.jpg", "Mountain")
        // mock the behavior of JOptionPane
        // every time JOptionPane.showInputDialog is called, it will instead just return "Hill"
        Mockito.mockStatic(JOptionPane::class.java).use { mockedJOptionPane ->
            mockedJOptionPane.`when`<String> {
                JOptionPane.showInputDialog(
                    Mockito.any(),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyInt(),
                    Mockito.any(),
                    Mockito.any(),
                    Mockito.any()
                )
            }.thenReturn("Hill")
            assertNotEquals("Mountain", wt.displayEntry(entry))
            // also check the other model data
            assertEquals(0, wt.getModel().countCorrect)
            assertEquals(1, wt.getModel().countFalse)
            assertEquals(false, wt.getPrevious())
        }
    }

    /**
     * Tests if the displayEntry function of the Worttrainer class will return null
     * when the user closes the dialog. Also checks if the model data is updated correctly.
     */

    @Test
    fun displayEntryTestNull(){
        val wt = Worttrainer(JSONPersistence())
        val entry = Entry("https://cdn.pixabay.com/photo/2017/02/14/03/03/ama-dablam-2064522_1280.jpg", "Mountain")
        // mock the behavior of JOptionPane
        // every time JOptionPane.showInputDialog is called, it will instead just return null
        Mockito.mockStatic(JOptionPane::class.java).use { mockedJOptionPane ->
            mockedJOptionPane.`when`<String> {
                JOptionPane.showInputDialog(
                    Mockito.any(),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyInt(),
                    Mockito.any(),
                    Mockito.any(),
                    Mockito.any()
                )
            }.thenReturn(null)
            assertEquals(null, wt.displayEntry(entry))
            // also check the other model data
            assertEquals(0, wt.getModel().countCorrect)
            assertEquals(0, wt.getModel().countFalse)
            assertEquals(null, wt.getPrevious())
        }
    }
}