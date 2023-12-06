fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { string ->
            val tw = string.filter { it.isDigit() }.toList()
            "${tw.first()}${tw.last()}".toInt()
        }
    }

    fun part2(input: List<String>) = input
        .sumOf { line ->
            (0..line.length).mapNotNull { index ->
                val tail = line.substring(index)
                Digit.entries.firstOrNull { digit ->
                    tail.startsWith(digit.text) || tail.startsWith("${digit.number}")
                }?.number
            }.let { 10 * it.first() + it.last() }
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

enum class Digit(val text: String, val number: Int) {
    ONE("one", 1),
    TWO("two", 2),
    THREE("three", 3),
    FOUR("four", 4),
    FIVE("five", 5),
    SIX("six", 6),
    SEVEN("seven", 7),
    EIGHT("eight", 8),
    NINE("nine", 9),
}