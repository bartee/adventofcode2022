import javax.lang.model.type.NullType

fun main() {
  var winning_combo_lookup = mapOf("rock" to "scissors", "scissors" to "paper", "paper" to "rock")
  val shape_score_lookup = mapOf("rock" to 1, "paper" to 2, "scissors" to 3)

  fun charToShape(input: String): String{
    var rockArray = arrayOf("A", "X")
    var paperArray = arrayOf("B", "Y")
    var scissorArray = arrayOf("C", "Z")

    if ( rockArray.contains(input)){
      return "rock"
    }
    if ( paperArray.contains(input)){
      return "paper"
    }
    if (scissorArray.contains(input)){
      return "scissors"
    }
    return "Unknown"
  }

  fun doIWin(me:String, opponent: String): String {
    if (me == opponent) {
      return "draw"
    }
    var winning = winning_combo_lookup.get(opponent)
    if (me != winning) {
      return "me"
    }
    return "opponent"
  }

  fun getScoreByResponse(response: String, counterShape: String): Int{
    var shapeScore = 0
    var matchScore = 0
    if (response == "Y") {
      shapeScore = shape_score_lookup.getOrDefault(counterShape,0)
      matchScore = 3
    }
    if (response == "X") {
      // you should lose, so I should not have the current or the winning shape
      var myShape = winning_combo_lookup.get(counterShape)
      shapeScore = shape_score_lookup.getOrDefault(myShape,0)
    }
    if (response == "Z") {
      // you should win
      var lookupPos = winning_combo_lookup.values.indexOf(counterShape)
      var myShape = winning_combo_lookup.keys.elementAt(lookupPos)
      shapeScore = shape_score_lookup.getOrDefault(myShape,0)
      matchScore = 6
    }
    return matchScore + shapeScore
  }

  fun scoreRound(me: String, opponent: String): Int {
    var score = 0

    var winner = doIWin(me, opponent)
    if (winner == "draw") {
      score = 3
    } else if (winner == "me" ){
      score = 6
    }

    val shapeScore = shape_score_lookup.getOrDefault(me,0)
    score += shapeScore
    return score
  }

  fun part1(input: List<String>): Int {
    var score = 0
    input.map{

      var entries = it.split(" ")
      
      var player1_choice = charToShape(entries[0])
      var player2_choice = charToShape(entries[1])

      var res = scoreRound(player2_choice, player1_choice)
      score += res
    }
    return score
  }

  fun part2(input: List<String>): Int {
    var score = 0
    input.map {
      var entries = it.split(" ")
      var player1_choice = charToShape(entries[0])
      var roundRes = getScoreByResponse(entries[1], player1_choice)
      score += roundRes
    }
    return score
  }

  // test if implementation meets criteria from the description, like:
  val test_input = readInput("resources/day02_example")
  check(part1(test_input) == 15)
  check(part2(test_input) == 12)

  // Calculate the results from the input:
  val input = readInput("resources/day02_dataset")
  println("Total score by shape: " + part1(input))
  println("Total score by outcome: " + part2(input))
}
