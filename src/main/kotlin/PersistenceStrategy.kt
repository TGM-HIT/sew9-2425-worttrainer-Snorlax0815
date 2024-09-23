package tgm.mrafeiner

interface PersistenceStrategy{
    fun save(m: Model): Unit;
    fun load(): Model;
    val defaultLocation: String;
}