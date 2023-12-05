import comms.CommDevice

fun main(){
  fun findMarkerPos(buffer: String) : Int {
    val device = CommDevice()
    return device.findStartOfPacketMarkerPos(buffer)
  }

  fun findMessageMarkerPos(buffer: String) : Int {
    val device = CommDevice()
    return device.findStartOfMessageMarkerPos(buffer)
  }

  val testInput = readInput("resources/day06_example").joinToString("")
  check(findMarkerPos(testInput) == 7)
  check(findMessageMarkerPos(testInput) == 19)

  val input = readInput("resources/day06_dataset").joinToString("")

  val markerpos = findMarkerPos(input)
  println("The marker has been localized at $markerpos")
  val messagepos =findMessageMarkerPos(input)
  println("The message has been localized at $messagepos")
}