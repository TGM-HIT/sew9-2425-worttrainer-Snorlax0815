package tgm.mrafeiner

fun main() {
    println("Hello World!")
    val wt = Worttrainer(JSONPersistence())
    wt.run()
}