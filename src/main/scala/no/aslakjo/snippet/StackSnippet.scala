package no.aslakjo.snippet

import net.liftweb.util._
import Helpers._

import scala.collection.mutable.{Stack}
import net.liftweb.http.{SessionVar, RequestVar,SHtml}
import java.util.{GregorianCalendar, Date}
import model.{StackElement, StackItem}
import lib.StackStore

object Timer extends RequestVar(new Date())

trait StartedAt{
  def started = "*" #> <div style="color: #c1c1c1">{Timer.get}</div>
}

class StackSnippet extends StartedAt{

  def push ={
    "#field" #> SHtml.text("", newElement => {

      val stackElement = new StackElement(newElement)
      StackStore.push(stackElement)
    })
  }

  def list = {
    "#list *" #> StackStore.all.map(_.description)
  }
  
  def pop = {

    "#pop" #> SHtml.button("Pop", ()=> StackStore.pop)
  }


}

class StackCounter extends StartedAt{

  def size = {
    "#count" #> <span>Antall: {StackStore.size}</span>
  }

}
