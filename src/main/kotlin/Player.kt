import kotlin.random.Random

class Player(val name: String, cardsAmount: Int = 1) {
    val cards = mutableListOf<Card>()

    init {  // раздаем карточки
        for (i in 1..cardsAmount) cards.add(Card())
    }

    fun checkCards(number: Int) {
        cards.forEach { it.getCell(number)?.fill() }
    }

    fun drawCards() {
        cards.forEachIndexed { index, card ->
            println("Карта #${index + 1}")
            card.draw()
        }
    }

    fun checkWin(): GameState {
        return if (cards.any { it.isFulfilled }) GameState(true, this)
        else GameState(false, null)
    }

    companion object {

        fun generateName(): String {
            val vowels = listOf("a", "o", "u", "i", "e")
            val consonants = listOf("n", "m", "t", "r", "v", "p", "g", "s")
            val pieces = mutableListOf<String>()
            consonants.forEach { c -> vowels.forEach { v -> pieces.add(c + v) } }

            var name = ""
            for (i in 1..Random.nextInt(2, 5)) name += pieces.random()
            return name.capitalize()
        }
    }
}