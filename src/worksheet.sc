val s = "mio testo #tag e poi #tag2 #pippo"

val k = s.split("\\s+").toList

val c = k.size

val l = k.filter(_.startsWith("#")).map(_.substring(1))


import uk.co.myspots.model.RestApiJsonProtocol._
import uk.co.myspots.model._
import spray.json._

val s1 = Spot("www", "desc", 0 , "pippo" , 0)

implicit val spotFormat= jsonFormat5(Spot)
val cJson = s1.toJson






