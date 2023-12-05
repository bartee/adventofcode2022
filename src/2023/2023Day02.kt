
data class GameResult (val ID: Int, val resultset: Map<String, Int>)
private fun calculateGameResult(it: String): GameResult{
  val (gamestr, setsstr) = it.split(":")
  var maxes = mutableMapOf<String, Int>()

  // Create the set and the
  val ID = gamestr.split(" ")[1].toInt()
  val sets = setsstr.split(';')

  for (set in sets) {
    val colorsets = set.split(",")
    for (colorset in colorsets) {
      var (count, color) = colorset.trim().split(" ")
      val cur_max = maxes.getOrDefault(color, 0)
      if (count.toInt() > cur_max) {
        maxes.put(color, count.toInt())
      }
    }
  }

  val result = GameResult(ID, maxes)
  return result
}

fun validateGameResult(input: String, requiredOutcome: Map<String, Int>): Int {
  val gameResult = calculateGameResult(input)

  // check if the game matches the desired outcome
  var valid = true

  requiredOutcome.forEach{ entry ->
    val maxed_val = gameResult.resultset.getOrDefault(entry.key, 1000)
    if (maxed_val > entry.value)
      valid = false
  }

  if (valid) return gameResult.ID
  return 0
}

fun calculateGamePower(input: String): Int {
  val gameResult = calculateGameResult(input)
  val power = gameResult.resultset.getOrDefault("red",0) * gameResult.resultset.getOrDefault("green", 0)*gameResult.resultset.getOrDefault("blue", 0)
  return power
}

fun partOne(input: List<String>): Int {
  val requiredOutcome = mapOf<String, Int>("red" to 12, "green" to 13, "blue" to 14)

  var sum = 0
  input.map {
    val res = validateGameResult(it, requiredOutcome)
    sum = sum + res
  }
  return sum
}
fun partTwo(input: List<String>): Int {
  val requiredOutcome = mapOf<String, Int>("red" to 12, "green" to 13, "blue" to 14)

  var sum = 0
  input.map {
    val res = calculateGamePower(it)
    sum = sum + res
  }
  return sum
}

fun main(){
  val test_input = readInput("2023/resources/input/day02_test")
  val game_input = readInput("2023/resources/input/day02_gameresults")
  var res = partOne(game_input)
  println("Part one result: $res")

  var power = partTwo(game_input)
  println("Total game power: $power")

}