fun calibrate_line_numerically(input: String): Int {
  // get the numbers from the line
  val chars = input.toCharArray()
  val (digits, notDigits) = chars.partition { it.isDigit() }

  val first = digits.first()
  val last = digits.last()

  return "$first$last".toInt()
}

fun calibrate_line_verbose(input: String): Int{
  // Replace the first word with a number
  // replace the last word with a number
  //  var reverse = input.reversed()
  //  val testlist = arrayOf<String>("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
  var replaced = input.replace("one", "o1e")
    .replace("two", "t2o")
    .replace("three", "t3ree")
    .replace("four","f4ur")
    .replace("five", "f5ve")
    .replace("six","s6x")
    .replace("seven", "s7ven")
    .replace("eight", "e8ght")
    .replace("nine", "n9ne")

  val result = calibrate_line_numerically(replaced)
  return result
}

// Part 1
fun part_one(input: List<String>): Int {
  //  We'll be using a Calibration document which is hard to read.
  //  Combine the first and the last digit on every line to form a single two digit number
  //  Sum those calibration values to get the document value

  var score = 0
  input.map {
    score = score + calibrate_line_numerically(it)
  }
  return score
}

fun part_two(input: List<String>): Int {
  var score = 0
  input.map {
    val linescore = calibrate_line_verbose(it)

    score = score + linescore
    println("$score (adding $linescore)")
  }
  return score
}
fun main() {

//  Checking snow production:
//  - elves marked the top 50 problem locations regarding snow production.
//  - Check al 50 stars by dec. 25.
//  Collect them doing puzzles; two puzzles a day, every puzzle grants a star.
//

  val calibration_input = readInput("2023/resources/input/day01_calibrationdata")
  println("Calibrated data : " + part_one(calibration_input))
  // Correct answer: 56042
  val shortened_input = readInput("2023/resources/input/day01_test")
  println("Calibrated verbose data : " + part_two(calibration_input))
}