import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class GameState(val ended: Boolean, val winner: Player?)

fun main() {
    val game = Gamemaster()  // создаем ведущего и поток данных
    var state = GameState(false, null)  // начальное состояние
    val players = mutableListOf<Player>()  // список игроков
    var steps = 0  // считаем количество бочонков

    // создаем от двух до четырех игроков
    for (i in 1..Random.nextInt(2, 5))
        players.add(Player(
            name = Player.generateName(),
            cardsAmount = 2  // с двумя картами (можно больше, но будет нагромождение)
        ))

    println("=== НАЧИНАЕМ ИГРАТЬ! ===")

    // запускаем игру
    runBlocking {
        // подписываемся на поток данных и получаем данные с него
        // до тех пор, пока игра не будет окончена
        game.flow.takeWhile { !state.ended }.collect { number ->
            steps++  // считаем бочонки

            println("=== Из мешка появляется число $number! ===")
            for (player in players) {  // обходим всех игроков
                player.checkCards(number)  // заполняем подходящие числа на карточках

                // отрисовываем карточки
                println(" ".repeat(8) + "Карты игрока ${player.name}")
                player.drawCards()
                println()

                // если победитель не выявлен, пытаемся выявить
                if (!state.ended) state = player.checkWin()
            }
        }

        // как только игра заканчивается (а это единственный способ отписаться от потока),
        // пишем результат
        println("""=== ИГРА ОКОНЧЕНА! ===
            |Вытянуто бочонков: $steps / 90
            |Победитель: ${state.winner!!.name}
        """.trimMargin())
    }
}
