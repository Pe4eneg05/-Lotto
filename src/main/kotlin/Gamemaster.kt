import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow

class Gamemaster {
    private val _bag = (1..90).shuffled()  // мешок с бочонками

    // холодный поток с бочонками
    val flow = flow {
        for (number in _bag) {
            emit(number)
            delay(1000)
        }
    }
}
