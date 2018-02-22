import org.junit.Test

fun createFibonacci(): Sequence<Int> {
    var first = 1
    var second = 1
    return generateSequence {
        val sum = first + second
        first = second
        second = sum
        sum
    }
}

class FibonacciTest {
    @Test
    fun fiboTest1() {
        val seq = createFibonacci()
        val n = seq.take(100)
        println(n.toList())

    }
}