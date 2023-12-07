package `2023`

import readInput
data class IntIndex(val index: Int, val pos: Pair<Int, Int>)
fun createSymbolIndex(input: String): List<Int> {
  val symbol_pattern = """[^\d\w\.]+""".toRegex()
  return symbol_pattern.findAll(input).map { it.range.start }.toList<Int>()
}

fun createNumberIndex(input: String): List<IntIndex> {
  val int_pattern = """(\d)+""".toRegex()
  return int_pattern.findAll(input).map{ IntIndex(it.groupValues[1].toInt(), Pair(it.range.start.toInt(), it.range.endInclusive.toInt())) }.toList()
}
fun partOne(input: List<String>): Int {
  // var numberIndex = mutableMapOf<Int, List<IntIndex>>()
  val symbolIndex = input.mapIndexed { index, s ->  createSymbolIndex(s) }
  val numberIndex = input.mapIndexed{ index, s -> createNumberIndex(s) }

  symbolIndex.withIndex().forEach{
    val ind = it.index
    // find the min and the max
    val minNumberRow = (if ind -1 < 0) ? 0 : (ind -1)
    val maxNumberrow = (if ind +1 > numberIndex.size) ? numberIndex.size : (ind + 1)
  }
  return 0
}

fun partTwo(input: List<String>): Int {
  return 0
}

fun main() {
  val test_input = readInput("2023/resources/input/day03_testinput")
  val game_input = readInput("2023/resources/input/day03_gameinput")

  val testOne = partOne(test_input)
  val testTwo = partTwo(test_input)

  println("Testing ... ")
  assert(testOne == 4361)
//  assert(testTwo == 2286)
  println("Tested! ")

  var res = partOne(game_input)
  println("Part one result: $res")

}