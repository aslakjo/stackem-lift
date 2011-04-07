package model

import net.liftweb.mongodb.record.{MongoMetaRecord, MongoId, MongoRecord}
import net.liftweb.record.field.{StringField, DateTimeField}

class StackItem extends MongoRecord[StackItem] with MongoId[StackItem] {
  def meta = StackItem

  object description extends StringField(this, 1024)
  object createdAt extends DateTimeField(this)
}

object StackItem extends StackItem with MongoMetaRecord[StackItem] {
  
}