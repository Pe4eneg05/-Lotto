class Card {
    val cells = mutableListOf<Cell>()
    val isFulfilled: Boolean
        get() = cells.none { !it.isFilled }

    init {
        for (row in 1..3) {  // каждый из трех рядов...
            var generated = 0
            while (generated < 5) {  // ...пытаемся заполнить на пять клеток
                val cell = Cell.generateCellInRow(row)  // создаем клетку
                if (getCell(cell.position) != null || getCell(cell.number) != null)
                    continue  // проверяем наличие клетки в том же месте или с тем же номером
                cells.add(cell)  // добавляем клетку
                generated++  // увеличиваем счетчик заполнения ряда
            }
        }
    }

    fun getCell(position: CellPosition): Cell? {
        return cells.find { it.position == position }
    }


    fun getCell(number: Int): Cell? {
        return cells.find { it.number == number }
    }

    fun draw() {
        for (row in 1..3) {  // проходим строчки
            for (col in 1..9) {  // потом столбцы
                val cell = cells.find { it.position == CellPosition(row, col) }
                if (cell == null) print(" __ ")  // клетка не участвует в игре
                else print(cell)  // клетка участвует в игре
            }
            println()  // перенос на следующую строчку
        }
    }
}