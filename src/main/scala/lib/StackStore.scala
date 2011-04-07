package lib

import model.{StackItem, StackElement}
import java.util.GregorianCalendar
import net.liftweb.common.{Empty, Box}
;
object StackStore {
  def push(element: StackElement) = StackItem.createRecord
        .description(element.description)
        .createdAt(new GregorianCalendar())
        .save

  def all = allFromBacking.map( item => new StackElement(item.description.get))
  def pop = Box(first).choice((head:StackItem) => {StackItem.delete_!(head); Empty} )(Empty)
  def size = all.size

  private def first = allFromBacking.headOption
  private def allFromBacking = StackItem.findAll.sortBy(_.createdAt.get.getTimeInMillis * -1)
  
}