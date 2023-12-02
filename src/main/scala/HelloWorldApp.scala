
import javax.swing.table.TableModel
import scala.swing._
import scala.swing.Swing


object HelloWorldApp {
  def main(args: Array[String]) : Unit = {
    println("Hello world")

    val linkedList = new LinkedList[Int](123)


    linkedList.AddFirst(1)
    linkedList.AddFirst(2)
    var node = linkedList.AddFirst(3)


    node = linkedList.AddLast(4)

    //var index: Int = 1
    //linkedList.AddAt(value = 7,index = 0)
    linkedList.AddAfter(linkedList.AddAt(value = 7,index = 0),8)

    linkedList.Remove(node)


    println(linkedList.toString)
    linkedList.QuickSort()


    var mainFrameView = new MainFrameView
    mainFrameView.ViewFrame



  }
}