package Stacks

open class CrateMover9000(var stacks: MutableMap<Int, MutableList<String>>, var moves: MutableList<Map<String, Int>>) {

  fun moveAll(){
    for (move in this.moves) {
      this.move(move.getOrDefault("number", 0), move.getOrDefault("source",-1), move.getOrDefault("target", -1))
    }
  }

  open fun move(number: Int, from: Int, to: Int){
    for (i in 1..number) {
      val source= this.stacks[from]?.removeLast()
      this.stacks[to]?.add(source ?: "")
    }
  }

  fun getTopElements() : String {
    var res = ""

    for (key in this.stacks.keys.sorted().iterator()) {
      val value = this.stacks.getOrDefault(key, null)
      res += value?.last().toString()
    }
    return res
  }
}