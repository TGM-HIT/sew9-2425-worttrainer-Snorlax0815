package tgm.mrafeiner

import java.awt.Component
import java.awt.Image
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JOptionPane

/**
 * The Worttrainer class.
 * Displays an image and asks for the value.
 * The data model is saved and loaded using a PersistenceStrategy.
 */
class Worttrainer(p: PersistenceStrategy) {
    private var model: Model;
    private var persistence: PersistenceStrategy;
    private var previous: Boolean? = null;


    init {
        this.persistence = p;
        this.model = this.persistence.load()
    }

    /**
     * Runs the Worttrainer.
     * If the input is empty, the data model is saved and the program ends.
     * The program will display a random entry from the data model and ask for the value.
     */
    public fun run(){
        // Endless loop until the ineput is empty, like the desc said.
        println("Worttrainer start")
        while(true){
            val randIndex: Int = (0 until this.model.entries.size).random();
            val e: Entry = this.model.entries[randIndex];
            val input = this.displayEntry(e);
            // at the end save the data model
            if(input == null){
                this.save()
                break;
            }
        }
        println("Worttrainer end")
    }

    /**
     * Displays an entry to the user and asks for the value.
     * Updates the data model with the result.
     * @param e the entry to display
     * @return the value the user entered
     */
    public fun displayEntry(e: Entry): String?{
        println(e.URL);
        println(e.value);
        var image: Image = ImageIO.read(URL(e.URL));
        image = image.getScaledInstance(360, 244, Image.SCALE_SMOOTH)
        // create a new dialog with input, display the image and ask for the value
        val input = JOptionPane.showInputDialog(
            null,
            if (this.previous == null) "Was ist das Wort für dieses Bild?" else if (this.previous!!) "Richtig! Was ist das Wort hierfür?" else "Leider falsch! Was ist das Wort hierfür?",
            "Worttrainer" + if (this.previous!== null) ": ${this.model.countCorrect} richtig, ${this.model.countFalse} falsch" else "",
            JOptionPane.QUESTION_MESSAGE,
            ImageIcon(image),
            null,
            null,
        )
        println(input)
        // update the data model
        if(input == null || input == "") return null;
        if(input.toString()== e.value){
            this.model.countCorrect++;
            this.previous = true;
        }
        else{
            this.model.countFalse++;
            this.previous = false;
        }
        return input.toString();
    }

    /**
     * Loads the data model from the persistence strategy.
     */
    public fun load(){
        this.model = this.persistence.load();
    }

    /**
     * Saves the data model to the persistence strategy.
     */
    public fun save(){
        this.persistence.save(this.model);
    }

    // getter und Setter

    public fun getModel(): Model{
        return this.model;
    }

    public fun setModel(m: Model){
        this.model = m;
    }

    public fun getPrevious(): Boolean?{
        return this.previous;
    }

    public fun setPrevious(p: Boolean){
        this.previous = p;
    }
}