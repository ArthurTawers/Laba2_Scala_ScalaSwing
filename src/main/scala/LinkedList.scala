import java.io.InvalidObjectException
import scala.util.control.Breaks.{break, breakable}

class LinkedList[T <% Comparable[T]](var firstValue: T) {

  class Node[K](var value: K, var next: Node[K]) {
    println("Node class constructor with value " + value.toString)

    override def toString: String = {
      "{(" + value.toString + ")->" + (if (next.next == null) "XXX" else next.value.toString)
    }
  }
  println("Class LinkedList constructor")

  private var default: T = _
  private var currentNode: Node[T] = _

  private var Head = new Node[T](value = firstValue,null)
  private var Tail = new Node[T](value = firstValue,null)
  Head.next = Tail

  var Count = 0
  def First = if(Count == 0) null else Head.next

  def Current = currentNode.value

  def After(node: Node[T]) = {
    if(node == null) {
      throw new NullPointerException("node is null in 'After' method!!!!")
    }

    var node_current = node
    if(node_current.next == null) {
      throw new InvalidObjectException("Узел указанный как 'после' больше не в списке!!!")
    }

    if(node_current.next == Tail) null
    else node_current.next
  }

  def Find(value: T):Node[T] = {
    var node = Head.next

    while(!node.equals(Tail)) {
      if (node.value == value) return node
      else node = node.next
    }

    null
  }

  override def toString: String = {
    if(Count == 0) "[]"

    var retString = "["
    var k = 0

    var node = Head.next

    while(!node.equals(Tail)) {
      retString = retString.concat(node.toString)
      node = node.next
      if(k < Count - 1) {
        retString = retString.concat(",")
      }
      k = k + 1
    }

    retString = retString.concat("]")

    return retString
  }

  def AddFirst(value: T): Node[T] = {
    var new_node = new Node[T](value,Head.next)

    Head.next = new_node
    Count = Count+1

    return new_node
  }

  def AddLast(value: T): Node[T] = {
    var node = Head
    if(Count == 0) {
      return AddFirst(value)
    }
    while(node.next.next != Tail) {
      node = node.next
    }

    var new_node = new Node[T](value,Tail)
    node.next.next = new_node

    Count = Count+1

    return new_node
  }

  def AddAt(value: T, index: Int):Node[T] = {
    var k = 0

    var node = Head

    if(index == Count) {
      return AddLast(value)
    }

    if(index < 0 || index > Count)
      throw new ArrayIndexOutOfBoundsException("Индекс вне границ массива")


    breakable {
      while (!node.equals(Tail)) {
        if (k == index) {
          return AddAfter(node, value)
        }

        node = node.next

        if (k == Count - 1) {
          break
        }
        k = k + 1
      }
    }

    throw new ArrayIndexOutOfBoundsException("Индекс вне границ списка AddLast")
  }

  def AddAfter(after: Node[T], value: T): Node[T] = {
    var current_node = after
    var new_node = new Node[T](value, current_node.next)
    current_node.next = new_node

    Count = Count + 1

    return new_node
  }

  def Clear = {
    var node = First


    for(i <- 0 to Count){
      if(node != null){
        Remove(node)
        node = After(node)
      }
    }

    Count = 0
  }

  def Remove(node: Node[T]) = {
    if(node == null){
      throw new IllegalArgumentException("node = null Remove")
    }
    if(Find(node.value) == null){
      throw new InvalidObjectException("node.value not faund")
    }

    var remove = node
    var current_node = Head

    while (!current_node.next.next.equals(remove.next)){
      current_node = current_node.next
    }
    current_node.next = remove.next

    Count = Count - 1
  }

  def RemoveFirst(): Unit = {
    if(Count == 0) {
      throw new InvalidObjectException("Remove")
    }

    Remove(First)
  }

  def RemoveLast() = {
    if(Count == 0) {
      throw new InvalidObjectException("RemoveLast")
    }
    var current_node = Head

    while (!current_node.next.next.equals(Tail)){
      current_node = current_node.next
    }

    current_node.next = Tail

    Count = Count - 1
  }

  def MoveNext(): Boolean = {
    currentNode = currentNode.next
    return  currentNode != Tail
  }

  def Reset() = {
    currentNode = Head
  }

  def RemoveAt(index: Int): Node[T] = {
    var currentNode = First
    var i = 0

      while (i <= index && currentNode != null) {
        breakable{
          if (i != index) {
            currentNode = currentNode.next
            i = i + 1
            break
          }
        Remove(currentNode)
        return currentNode
      }
    }
    return currentNode
  }

  private def partion(firsting: Node[T], lasting: Node[T]): Node[T] = {
    var first = firsting
    var last = lasting
    var pivot: Node[T] = first
    var front: Node[T] = first
    var temp: T = first.value

    while (front != null && !front.equals(last)){
      if( front.value.compareTo(last.value) < 0) {
        pivot = first
        temp = first.value
        first.value = front.value
        front.value = temp
        first = first.next
      }

      front = front.next
    }

    temp = first.value
    first.value = last.value
    last.value = temp
    return  pivot
  }

  private def quickSort(first: Node[T], last: Node[T]): Unit= {
    if(first == last) return

    var pivot = partion(first,last)
    if(pivot != null && pivot.next != null){
      quickSort(pivot.next,last)
    }
    if(pivot != null && first != pivot){
      quickSort(first,pivot)
    }
  }

  def QuickSort() = {
    var node = Head.next

    while (!node.next.equals((Tail))){
      node = node.next
    }

    quickSort(Head.next, node)
  }

}
