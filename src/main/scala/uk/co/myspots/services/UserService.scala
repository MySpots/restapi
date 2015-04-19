package uk.co.myspots.services

import akka.actor.ActorRef
import spray.http.StatusCodes
import spray.json._
import spray.routing.HttpService
import uk.co.myspots.actors.CreateUser
import uk.co.myspots.model.{RestApiJsonProtocol, User}

trait UserService extends HttpService {

  import RestApiJsonProtocol._

  def userRoute(userActor: ActorRef) =
    path("user" / Segment) { username =>
      put {
        entity(as[User]) { user => //extract json Bar from post body
          complete {
            userActor ! CreateUser(user)
            "Created user " + username + "  " + user.toJson
            StatusCodes.Created
          }
        }
      }
    }

}
