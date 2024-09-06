package tgm.mrafeiner

import java.awt.Component
import java.awt.Image
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JOptionPane

class Worttrainer(p: PersistenceStrategy) {
    private var model: Model;
    private var persistence: PersistenceStrategy;
    private var previous: Boolean? = null;

    init {
        this.persistence = p;
        this.model = this.persistence.load()
    }

    public fun run(){
        // Endless loop until the ineput is empty, like the desc said.
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
            "Worttrainer",
            JOptionPane.QUESTION_MESSAGE,
            ImageIcon(image),
            null,
            null,
        )
        println(input)
        // update the data model
        if(input == null) return null;
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

    public fun load(){
        this.model = this.persistence.load();
    }

    public fun save(){
        this.persistence.save(this.model);
    }

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