package comms

class CommDevice{

  fun findStartOfPacketMarkerPos(buffer: String) : Int {
    return findStartPos(buffer, 4)
  }

  fun findStartOfMessageMarkerPos(buffer: String): Int {
    return findStartPos(buffer, 14)
  }
  private fun findStartPos(buffer: String, markerLength: Int) : Int{
    var startpos = 0
    var endpos = markerLength
    while (endpos < buffer.length){
      val testmarker = buffer.subSequence(startpos, endpos).toList()

      if (testmarker.distinct().size == markerLength ) {
        return endpos
      }
      startpos += 1
      endpos += 1
    }
    return -1
  }

}