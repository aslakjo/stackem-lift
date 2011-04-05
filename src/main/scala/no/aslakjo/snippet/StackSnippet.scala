package no.aslakjo.snippet

import net.liftweb.util._
import Helpers._

import scala.collection.mutable.{Stack}
import java.util.Date
import net.liftweb.http.{StatefulSnippet, SessionVar, RequestVar, SHtml}

object Timer extends RequestVar(new Date())

trait StartedAt{
  def started = "*" #> <div style="color: #c1c1c1">{Timer.get}</div>
}

class StackSnippet extends StartedAt with StatefulSnippet{
  private val stack = new Stack[String]()

  val dispatch :DispatchIt = {
    case "form" => form
    case "started" => started
  }

  def form = {
    "#field" #> SHtml.text("", newElement => {
      stack.push(newElement)
      println(stack, newElement)
    }) &

    "#pop" #> SHtml.button("Pop", {
      println(stack)
      stack.pop
    }) &
    "#list *" #> {
      println(stack)
      stack
    }
  }

}

class StackCounter extends StartedAt{

  def size = {
    "#count" #> <span>Antall: (unable)</span>
  }

}
