package tgm.mrafeiner

fun main() {
    println("Hello World!")
    val wt = Worttrainer(JSONPersistence())
    wt.displayEntry(Entry("https://cdn.pixabay.com/photo/2017/02/14/03/03/ama-dablam-2064522_1280.jpg", "Mountain"))
}