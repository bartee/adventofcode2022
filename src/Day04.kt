fun main () {

  fun createRangeFromDelimiters(it: String) : List<Int> {
    val borders = it.split("-")
    val list = mutableListOf<Int>()
    for (i in borders[0].toInt().rangeTo(borders[1].toInt())) {
      list.add(i)
    }
    return list
  }

  fun isOverlapping(list1: List<Int>, list2: List<Int>): Boolean {
    // Check if list1 exists in list2
    println("Verifying (${list1.first()}-${list1.last()} and ${list2.first()}-${list2.last()})")
    if (list1.last() < list2.first() || list1.first() > list2.last()){
      println("Lists are way out of range (${list1.first()}-${list1.last()} and ${list2.first()}-${list2.last()})")
      return false
    }
    if (list1.first() >= list2.first() && list1.last() <= list2.last()){
      println("The second list contains the first one")
      return true
    } else if (list2.first() >= list1.first() && list2.last() <= list1.last()) {
      println("The first list contains the second one")
      return true
    }
    println("NO - list1 and list2 do not overlap")
    return false
  }

  fun isPartiallyOverlapping(list1: List<Int>, list2: List<Int>): Boolean {
    val res1 = list1.intersect(list2)
    val res2 = list2.intersect(list1)
    if (res1.size > 0 || res2.size > 0){
      println("${res1.size} items from list1 overlap list2, or ${res2.size} items from list 2 overlap list 1")
      return true
    }
    return false
  }

  fun findFullyOverlappingSectionSequences(input:List<String>): Int {
    var sequencesFound = 0
    input.map {

      val lists = it.split(",").map {
        createRangeFromDelimiters(it)
      }

      val list1 = lists.first()
      val list2 = lists.last()

      if(isOverlapping(list1, list2)){
        sequencesFound +=1
      }

    }
    println("$sequencesFound overlaps found")
    return sequencesFound
  }

  fun findPartiallyOverlappingSectionSequences(input:List<String>): Int {
    var sequencesFound = 0
    input.map {

      val lists = it.split(",").map {
        createRangeFromDelimiters(it)
      }

      val list1 = lists.first()
      val list2 = lists.last()

      if(isPartiallyOverlapping(list1, list2)){
        sequencesFound +=1
      }

    }
    println("$sequencesFound overlaps found")
    return sequencesFound
  }
  // test if implementation meets criteria from the description, like:
  val test_input = readInput("resources/day04_example")
  check(findFullyOverlappingSectionSequences(test_input) == 2)
  check(findPartiallyOverlappingSectionSequences(test_input) == 4)
//  check(part2(test_input) == 12)

  // Calculate the results from the input:
  val input = readInput("resources/day04_dataset")
  findFullyOverlappingSectionSequences(input)
  findPartiallyOverlappingSectionSequences(input)
}