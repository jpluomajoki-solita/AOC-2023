import java.io.File

fun main(args: Array<String>) {
    day1()
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
