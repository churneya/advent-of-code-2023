fun main() {
    fun part1(input: List<String>): Int {
        val listOfGame = getGames(input)
        return listOfGame
            .filter { it.valid }
            .sumOf { it.number }
    }

    fun part2(input: List<String>): Int {
        val listOfGame = getGames(input)
        return listOfGame
            .sumOf { game ->
                game.sets.maxOf { it.red } *
                game.sets.maxOf { it.green } *
                game.sets.maxOf { it.blue }
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

data class Game(
    val number: Int,
    val sets: List<Set>
) {
    val valid: Boolean
        get() {
            return !this.sets.any {
                it.green > 13 ||
                        it.red > 12 ||
                        it.blue > 14
            }

        }

    data class Set(
        val red: Int,
        val blue: Int,
        val green: Int,
    )
}

fun String.getDigit() = this.filter { it.isDigit() }.toInt()

fun getGames(input: List<String>): List<Game> {
    return input.map { game ->
        val (gameNumberString, sets) = game.split(":")

        val gameNumber = gameNumberString.getDigit()

        val listOfSets = mutableListOf<Game.Set>()
        sets.split(";").forEach { set ->
            var red = 0
            var blue = 0
            var green = 0
            set.split(",").forEach { cubes ->

                with(cubes) {
                    when {
                        contains("blue") -> blue = this.getDigit()
                        contains("green") -> green = this.getDigit()
                        contains("red") -> red = this.getDigit()
                    }
                }
            }
            listOfSets.add(
                Game.Set(
                    red = red,
                    blue = blue,
                    green = green
                )
            )
        }

        Game(
            number = gameNumber,
            sets = listOfSets
        )
    }
}