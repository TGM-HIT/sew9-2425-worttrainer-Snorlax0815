package tgm.mrafeiner

import org.json.JSONObject
import java.io.File
import java.io.IOException

/**
 * JSONPersistence is a class that implements the PersistenceStrategy interface.
 * It is used to save and load the model to and from a JSON file.
 * The default location of the file is save.json
 * @author Markus Rafeiner
 * @version 2024-09-06
 */
class JSONPersistence(private var location: String) : PersistenceStrategy {
    override val defaultLocation: String = "save.json"

    init{
        if(this.location == ""){
            this.location = defaultLocation
        }
    }

    /**
     * Saves the model to a JSON file
     */
    override fun save(m: Model) {
        val json = JSONObject()
        json.put("countCorrect", m.countCorrect)
        json.put("countFalse", m.countFalse)
        json.put("entries", m.entries)
        // println(json.toString())
        val file = File(this.location)
        try {
            file.createNewFile()
            file.setWritable(true, false)
            // println(file.absolutePath)
            if (file.canWrite()) {
                val writer = java.io.FileWriter(file)
                json.write(writer, 4, 0)
                writer.flush()
                writer.close()
            } else {
                error("Cannot write to file $location")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            error("Cannot write to file $location")
        }
    }

    /**
     * Loads the model from a JSON file
     * If the file does not exist, a default model is returned
     */
    override fun load(): Model {
        if (!File(this.location).exists()) {
            // load defaults and return
            return Model(listOf(Entry("https://cdn.pixabay.com/photo/2017/02/14/03/03/ama-dablam-2064522_1280.jpg", "Mountain")), 0, 0)
        }
        val json = JSONObject(java.io.FileReader(location).readText())
        val entries = json.getJSONArray("entries")
        val entriesList = mutableListOf<Entry>()
        for (i in 0 until entries.length()){
            val entry = entries.getJSONObject(i)
            entriesList.add(Entry(entry.getString("URL"), entry.getString("value")))
        }
        return Model(
            entriesList,
            json.getInt("countCorrect"),
            json.getInt("countFalse")
        )
    }


}