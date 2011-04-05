package no.aslakjo.snippet

import net.liftweb.util._
import Helpers._

import scala.collection.mutable.{Stack}
import net.liftweb.http.{SessionVar, RequestVar,SHtml}
import model.StackItem
import java.util.{GregorianCalendar, Date}

object Stacker extends SessionVar(Stack[String]())
object Timer extends RequestVar(new Date())

trait StartedAt{
  def started = "*" #> <div style="color: #c1c1c1">{Timer.get}</div>
}

class StackSnippet extends StartedAt{

  def push ={
    "#field" #> SHtml.text("", newElement => {
      Stacker.get.push(newElement)
      val stack = StackItem.createRecord
        .description(newElement)
        .createdAt(new GregorianCalendar())
        .save
    })
  }

  def list = {
    "#list *" #> all.map(_.description)
  }
  
  def pop = {
    val first = if(all.isEmpty)
      None
    else
      Some(all.head)

    "#pop" #> SHtml.button("Pop", ()=> first match {
      case None => Unit
      case Some(first) => StackItem.delete_!(first)
    })
  }

  private def all = StackItem.findAll.sortBy(_.createdAt.get.getTimeInMillis * -1)
}

class StackCounter extends StartedAt{

  def size = {
    "#count" #> <span>Antall: {Stacker.is.size}</span>
  }

}
