import java.io.File
import kotlin.math.min

fun main(args: Array<String>) {
    day2()
}

fun day1() {
    val part1 =
        File("src/main/resources/d1_test")
            .readLines()
            .map { it -> it.filter { it.isDigit() } }
            .sumOf { (it.first().toString() + it.last().toString()).toInt() }

    val numbersMap = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
        "zero" to 0,
    )


    val part2 =
        File("src/main/resources/d1_2_test")
            .readLines()
            .sumOf {
                var num1: String? = null
                var txt1 = ""
                var x = 0
                while (x < it.length && num1 == null) {
                    txt1 += it[x]
                    if (txt1.any { t1 -> t1.isDigit() }) {
                        num1 = txt1.filter { s -> s.isDigit() }
                    } else if (numbersMap.keys.any { num -> num in txt1 }) {
                        num1 = numbersMap[numbersMap.keys.first { num -> num in txt1 }].toString()
                    }
                    x++
                }

                var num2: String? = null
                var txt2 = ""
                x = it.length - 1
                while (x != -1 && num2 == null) {

                    txt2 = it[x] + txt2
                    if (txt2.any { t2 -> t2.isDigit() }) {
                        num2 = txt2.filter { s -> s.isDigit() }
                    } else if (numbersMap.keys.any { num -> num in txt2 }) {
                        num2 = numbersMap[numbersMap.keys.first { num -> num in txt2 }].toString()
                    }
                    x--
                }

                (num1 + num2).toInt()
            }


    println("part 1: $part1")
    println("part 2: $part2")
}

fun day2() {
    val input = File("src/main/resources/d2_actual").readLines()

    val colorCounts = mapOf(
        "blue" to 14,
        "green" to 13,
        "red" to 12
    )

    val badGames = mutableSetOf<Int>()
    val allGames = mutableSetOf<Int>()

    var minBlues = mutableListOf<Int>()
    var minGreens = mutableListOf<Int>()
    var minReds = mutableListOf<Int>()



    input.mapIndexed { i, game ->

        minBlues.add(0)
        minGreens.add(0)
        minReds.add(0)

        game.split(": ")[1].split("; ").map { round ->

            round.split(", ").map { cubes ->

                var colorAndCount = cubes.split(" ")
                var count = colorAndCount[0].toInt()
                var color = colorAndCount[1]

                allGames.add(i+1)


                when (color)  {
                    "blue" -> minBlues[i] = intArrayOf(count, minBlues[i]).max()
                    "green" -> minGreens[i] = intArrayOf(count, minGreens[i]).max()
                    "red" -> minReds[i] = intArrayOf(count, minReds[i]).max()
                }


                if (colorCounts[color]!! < count) {
                    badGames.add(i+1)
                }

            }

        }

    }


    var res = 0;
    for (i in 0..<minBlues.count()) {
        res += minBlues[i]*minGreens[i]*minReds[i]

    }

    println("part 1: ${(allGames-badGames).sum()}")
    println("part 2: $res")

}