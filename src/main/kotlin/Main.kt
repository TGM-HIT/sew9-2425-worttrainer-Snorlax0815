package tgm.mrafeiner

fun main() {
    println("Hello World!")
    val wt = Worttrainer(JSONPersistence())
    var model = Model(
        listOf(
            Entry("https://cdn.pixabay.com/photo/2017/02/14/03/03/ama-dablam-2064522_1280.jpg", "Mountain"),
            Entry("https://cdn.pixabay.com/photo/2022/01/12/19/28/mountains-6933693_1280.jpg", "Cabin"),
            Entry("https://cdn.pixabay.com/photo/2022/07/22/19/42/marmot-7338831_1280.jpg", "Animal"),
            Entry("https://cdn.pixabay.com/photo/2019/06/23/05/46/mountains-4292912_1280.jpg", "Lake")
        ),
        0,
        0
    )
    wt.setModel(model)
    wt.run()
}