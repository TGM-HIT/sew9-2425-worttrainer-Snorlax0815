package tgm.mrafeiner

class Worttrainer(p: PersistenceStrategy) {
    private var model: Model;
    private var persistence: PersistenceStrategy;

    init {
        this.persistence = p;
        this.model = this.persistence.load()
    }

    public fun run(){

    }

    public fun load(){

    }

    public fun displayEntry(e: Entry){

    }

    public fun save(){

    }
}