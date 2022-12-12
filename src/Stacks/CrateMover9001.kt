package Stacks


class CrateMover9001(stacks: MutableMap<Int, MutableList<String>>, moves: MutableList<Map<String, Int>>) :
  CrateMover9000(stacks, moves) {

  override fun move(
    number: Int,
    from: Int,
    to: Int
  ){
    val source = this.stacks[from]
    if (source != null) {
      val cratesToMove = source.subList((source.size - number), source.size).toMutableList()
      source.subList((source.size - number), source.size).clear()
      this.stacks[from] = source

      val targetStack = this.stacks.getOrDefault(to, null)
      targetStack?.addAll(cratesToMove)
      this.stacks[to] = targetStack!!.toMutableList()

    }
  }

}