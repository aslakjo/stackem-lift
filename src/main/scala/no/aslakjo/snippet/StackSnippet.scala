package no.aslakjo.snippet

import net.liftweb.util._
import Helpers._

import scala.collection.mutable.{Stack}
import net.liftweb.http.{SessionVar, RequestVar,SHtml}
import java.util.Date

object Stacker extends SessionVar(Stack[String]())
object Timer extends RequestVar(new Date())

trait StartedAt{
  def started = "*" #> <div style="color: #c1c1c1">{Timer.get}</div>
}

class StackSnippet extends StartedAt{

  def push ={
    "#field" #> SHtml.text("", newElement => {
      Stacker.get.push(newElement)
      println(Stacker.get, newElement)
    })
  }

  def list = {
    "#list *" #> Stacker.get
  }
  
  def pop = {
    "#pop" #> SHtml.button("Pop", Stacker.get.pop)
  }
}

class StackCounter extends StartedAt{

  def size = {
    "#count" #> <span>Antall: {Stacker.is.size}</span>
  }

}
