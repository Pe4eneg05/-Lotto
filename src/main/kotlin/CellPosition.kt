class CellPosition(val row: Int, val column: Int) {
    // переписываем `equals` для упрощения поиска по координатам в дальнейшем
    override fun equals(other: Any?): Boolean {
        if (other !is CellPosition) return false
        return row == other.row && column == other.column
    }

    // идея предложила создать `hashCode` - послушался ее
    override fun hashCode(): Int {
        var result = row
        result = 31 * result + column
        return result
    }
}