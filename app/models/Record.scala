package models

import play.api.Play.current
import java.util.Date
import util.MongodbColl
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import se.radley.plugin.salat._
import se.radley.plugin.salat.Binders._
import mongoContext._
import play.api.data.Form
import play.api.data.Forms._


case class Record (id: ObjectId = new ObjectId,
			  store: String,
			  serviceStatus: Int,
			  serviceStart: Date,
			  serviceEnd: Date,
			  serviceDesigner: String,
			  serviceItem: String,
			  userName: String,
			  userPhone: String,
			  userLeaveMessage:String,
			  costTotal:Int
			  )

object Record extends ModelCompanion[Record, ObjectId]{
  
  val dao = new SalatDAO[Record, ObjectId](collection= mongoCollection("record")){}
  
  def findById(id:ObjectId)= dao.findOne(MongoDBObject("id" -> id))
  
  def findAllrecord(store:String):List[Record] = dao.find(MongoDBObject("store" -> store)).toList
  
  def createRecord(record:Record) = dao.save(record, WriteConcern.Safe)
  
  def counts(store:String)=dao.count(MongoDBObject("store" -> store))
  
  def findByCondition(store:String,designer:String) = dao.find(MongoDBObject("store" -> store, "designer" -> designer )).toList
      
  
}