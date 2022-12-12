package Stacks

class InputParser//        This is an unparsable line// This is a letter, so we need to do stuff with it// it is dividable by 4, so it's a part of the stack.// This is a move. Store it.// this is the stack index. We can ignore it.
  (input: List<String>) {
  var stacks = mutableMapOf<Int, MutableList<String>>()
  var moves = mutableListOf<Map<String, Int>>()

  init {
    val reverseStacks = mutableMapOf<Int, List<String>>()
    val indexRegex = """[\s+\d]+""".toRegex()
    val stackRegex = """[\s*\[\w\]]+""".toRegex()
    val moveRegex = """move ([0-9]+) from ([0-9]+) to ([0-9]+)""".toRegex()
    input.map{
      if (it.matches(indexRegex)){
        // this is the stack index. We can ignore it.
      } else if (it.matches(moveRegex)){

        // This is a move. Store it.
        val matchResult = moveRegex.find(it)
        val (number, source, target) = matchResult!!.destructured
        val move = mapOf("number" to number.toInt(), "source" to source.toInt(), "target" to target.toInt())
        this.moves.add(move)

      } else if (stackRegex.matches(it)) {
        // it is dividable by 4, so it's a part of the stack.
        val chunks = it.chunked(4)

        chunks.mapIndexed { index, s ->
          val letter = s.trim()

          if (letter.length == 3) {
            // This is a letter, so we need to do stuff with it
            val str = letter.substring(1, 2)
            val selectedStack = reverseStacks.getOrDefault(index, mutableListOf<String>()).toMutableList()
            selectedStack.add(str)

            reverseStacks[index] = selectedStack
          }
        }
      } else {
//        This is an unparsable line
      }
    }
    this.equalizeStackLength(reverseStacks)
  }

  fun equalizeStackLength(stacks: MutableMap<Int, List<String>>){
    // Find the maximum length of all stacks.
    // reverse every stack, and pad them to the maximum length
    var max = 0
    for (item in stacks.values.iterator()) { max = maxOf(max, item.size) }

    stacks.forEach {
      val resultStack = " ,".repeat(max - it.value.size) + it.value.reversed().joinToString(",")
      this.stacks.set(it.key+1, resultStack.split(",").toMutableList())
    }
  }
}