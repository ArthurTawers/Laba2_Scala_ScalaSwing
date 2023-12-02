

import jdk.jfr.Enabled
import scala.util.Try
import javax.swing.JOptionPane
import scala.swing._
import scala.swing.Swing

class MainFrameView {
  var indexTextField = new TextField() {
    preferredSize = new Dimension(70, 20)
  }
  val linkedList = new LinkedList[Int](0)
  var listTextArea = new TextArea() {
    preferredSize = new Dimension(400, 70)
    enabled = false

  }


  var mainFrame = new Frame(){
    title = "Teplov linked list"
  }

  mainFrame.preferredSize = new Dimension(450,600)
  mainFrame.closeOperation()

  def ViewFrame = {
    var valueField = new TextField(){
      preferredSize = new Dimension(70,20)
    }
    valueField.preferredSize.setSize(600,600)

    mainFrame.contents = new FlowPanel {
      contents += new Label("число:")
      contents += valueField


      contents += new Button("Добавить в начало") {
        reactions += {
          case event.ButtonClicked(_) =>
            println("add first")
            var value = valueField.text.toIntOption
            if(value != None){
              linkedList.AddFirst(value.get)
            }
            else {
              JOptionPane.showMessageDialog(null,"число нормальн онапиши, а не " + listTextArea.text,"Warning!",JOptionPane.WARNING_MESSAGE)
            }
            listTextArea.text = linkedList.toString
        }
      }
      contents += new Button("Добавить в конец") {
        reactions += {
          case event.ButtonClicked(_) =>
            println("add last")
            var value = valueField.text.toIntOption
            if (value != None) {
              linkedList.AddLast(value.get)
            }
            else {
              JOptionPane.showMessageDialog(null, "число нормальн онапиши, а не " + listTextArea.text, "Warning!", JOptionPane.WARNING_MESSAGE)
            }
            listTextArea.text = linkedList.toString
        }
      }
//      contents += new Button("Добавить в конец") {
//        reactions += {
//          case event.ButtonClicked(_) =>
//            println("All the colours!")
//        }
//      }
      contents += new Label("index")
      contents += indexTextField
      contents += new Button("Добавить по индексу") {
        reactions += {
          case event.ButtonClicked(_) =>
            println("addAt")
            var value = valueField.text.toIntOption
            var index = indexTextField.text.toIntOption
            if (value != None && index != None) {
              try{
                linkedList.AddAt(value.get,index.get)
              }
              catch {
                case e: Exception => JOptionPane.showMessageDialog(null, "я щас тебя сам сломаю! введи нормальный индекс", "Warning!", JOptionPane.WARNING_MESSAGE)
              }

            }
            else {
              JOptionPane.showMessageDialog(null, "число или индекс напиши нормально, а не ", "Warning!", JOptionPane.WARNING_MESSAGE)
            }
            listTextArea.text = linkedList.toString
        }
      }
      contents += new Button("Удалить из начала") {
        reactions += {
          case event.ButtonClicked(_) =>
            println("remove first")
            var value = valueField.text.toIntOption
            if (value != None) {
              try {
                linkedList.RemoveFirst()
              }
              catch {
                case e: Exception => JOptionPane.showMessageDialog(null, "я щас тебя сам сломаю!" + e.getMessage.toString, "Warning!", JOptionPane.WARNING_MESSAGE)
              }

            }
            else {
              JOptionPane.showMessageDialog(null, "число или индекс напиши нормально, а не ", "Warning!", JOptionPane.WARNING_MESSAGE)
            }
            listTextArea.text = linkedList.toString
        }
      }
      contents += new Button("Удалить из конца") {
        reactions += {
          case event.ButtonClicked(_) => {
            println("remove last")
            try {
              linkedList.RemoveLast()
            }
            catch {
              case e: Exception => JOptionPane.showMessageDialog(null, "я щас тебя сам сломаю!" + e.toString, "Warning!", JOptionPane.WARNING_MESSAGE)
            }
            listTextArea.text = linkedList.toString

          }
        }
      }
      contents += new Button("Удалить по индексу") {
        reactions += {
          case event.ButtonClicked(_) =>
            println("removeAt")
            var index = indexTextField.text.toIntOption
            if (index != None) {
              try {
                linkedList.RemoveAt(index.get)
              }
              catch {
                case e: Exception => JOptionPane.showMessageDialog(null, "я щас тебя сам сломаю! введи нормальный индекс", "Warning!", JOptionPane.WARNING_MESSAGE)
              }
            }
            else {
              JOptionPane.showMessageDialog(null, "число или индекс напиши нормально!", "Warning!", JOptionPane.WARNING_MESSAGE)
            }
            listTextArea.text = linkedList.toString
        }
      }
      contents += new Button("Отсортировать") {
        reactions += {
          case event.ButtonClicked(_) =>
            try {
              linkedList.QuickSort()
              JOptionPane.showMessageDialog(null, "Сортировка сработала походу! ", "ESSSS!", JOptionPane.INFORMATION_MESSAGE)
            }
            catch {
              case e: Exception => JOptionPane.showMessageDialog(null, "я щас тебя сам сломаю!", "Warning!", JOptionPane.WARNING_MESSAGE)
            }

            listTextArea.text = linkedList.toString


        }
      }

      contents += listTextArea
    }
    mainFrame.centerOnScreen()

    mainFrame.maximumSize.setSize(5000,5000)
    mainFrame.maximized
    mainFrame.open()

  }

}
