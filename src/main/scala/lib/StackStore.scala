package lib

import model.{StackItem, StackElement}
import java.util.GregorianCalendar
import net.liftweb.common.{Empty, Box}

object StackStore extends StackableStore{}


trait StackableStore extends BackingStore {
  implicit def element2Item(element:StackElement) = StackItem.createRecord
          .description(element.description)
  implicit def item2Element(item:StackItem) = new StackElement(item.description.get)

  def push(element: StackElement) = addToBacking(element)
  def all:List[StackElement] = allFromBacking.map(item2Element(_))
  def pop = popFromBacking

  def size = all.size
}

trait BackingStore{
  def addToBacking(item: StackItem)= item.createdAt(new GregorianCalendar()).save
  def allFromBacking:List[StackItem] = StackItem.findAll.sortBy(_.createdAt.get.getTimeInMillis * -1)
  def popFromBacking = Box(first).choice((head:StackItem) => {StackItem.delete_!(head); Empty} )(Empty)

  private def first = allFromBacking.headOption
}

