package tgm.mrafeiner

fun main(args: Array<String>) {
    val wt: Worttrainer
    wt = if (args.isNotEmpty()) {
        Worttrainer(JSONPersistence(location = args[0]))
    }else{
        Worttrainer(JSONPersistence())
    }

    wt.run()
}