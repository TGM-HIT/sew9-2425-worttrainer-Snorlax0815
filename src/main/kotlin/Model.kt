package tgm.mrafeiner

data class Model(
    var entries: List<Entry>,
    var countCorrect: Int,
    var countFalse: Int
)
