package uk.co.myspots.model

import spray.httpx.SprayJsonSupport
import spray.json._

case class Spot(link: String, title: String, created: Long, user: User, lastAccess: Long, shortUrl: String, tags: List[String])

case class User(firstName: String, lastName: String, created: Long, userId: String)

object RestApiJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport {

  //  implicit val spotFormat= jsonFormat7(Spot)
  implicit val userFormat = jsonFormat4(User)
}



