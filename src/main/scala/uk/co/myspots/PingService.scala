package uk.co.myspots

import akka.actor.Actor
import spray.http.{StatusCodes, HttpEntity, HttpCharsets, MediaTypes}
import spray.httpx.unmarshalling.Unmarshaller
import spray.routing.{RequestContext, HttpService}
import spray.json._
import RestApiJsonProtocol._


class PingServiceActor extends Actor with PingService {

  override def actorRefFactory = context

  override def receive = runRoute(route)
}

trait PingService extends HttpService {

  lazy val route =
    path("ping") {
      get {
        complete {
          "PONG!"
        }
      }
    } ~
    path("user" / Segment){ userName =>
      put{

        entity(as[User]) { user => //extract json Bar from post body
            complete {
              "Created user " + userName + "  " + user.toJson
              StatusCodes.Created
            }
          }
        }
      }


}