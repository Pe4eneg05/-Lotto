import kotlin.random.Random

class Cell(val number: Int, val position: CellPosition) {
    var isFilled = false

    fun fill() { isFilled = true }

    // оверрайтим `toString` ради упрощения отрисовки
    override fun toString(): String {
        return if (isFilled) " ██ "  // клетка с установленным бочонком
        else " ${number.toString().padStart(2, ' ')} "  // клетка с цифрой
    }

    companion object {
        fun generateCellInRow(row: Int): Cell {
            return Cell(
                number = Random.nextInt(1, 91),
                position = CellPosition(row, Random.nextInt(1, 10))
            )
        }
    }
}