import Stacks.InputParser
import Stacks.CrateMover9000
import Stacks.CrateMover9001

fun main() {
  fun analyzeStackWithCrateMover9000(input:List<String>): String {
    val parser = InputParser(input)
    val manager = CrateMover9000(parser.stacks, parser.moves)
    manager.moveAll()
    val res = manager.getTopElements()
    return res
  }

  fun analyzeStackWithCrateMover9001(input:List<String>): String {
    val parser = InputParser(input)
    val manager = CrateMover9001(parser.stacks, parser.moves)
    manager.moveAll()
    val res = manager.getTopElements()
    return res
  }

  val test_input = readInput("resources/day05_example")
  check(analyzeStackWithCrateMover9000(test_input) == "CMZ")
  check(analyzeStackWithCrateMover9001(test_input) == "MCD")

  val input = readInput("resources/day05_dataset")
  val topElements: String = analyzeStackWithCrateMover9000(input)
  val topElements9001: String = analyzeStackWithCrateMover9001(input)

  println("The final elements for this stack using CrateMover9000 is $topElements")
  println("The final elements for this stack using CrateMover9001 is $topElements9001")

}
