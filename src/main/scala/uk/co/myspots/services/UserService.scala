package uk.co.myspots.services

import java.util.concurrent.TimeUnit

import akka.actor.ActorRef
import akka.util.Timeout
import spray.http.StatusCodes
import spray.json._
import spray.routing.HttpService
import uk.co.myspots.actors.{GetUser, CreateUser}
import uk.co.myspots.model.{RestApiJsonProtocol, User}
import akka.pattern.ask




trait UserService extends HttpService {

  import RestApiJsonProtocol._

  implicit val timeout = Timeout(5, TimeUnit.SECONDS)



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
      } ~
      get {
          complete {

            userActor ? GetUser(username)

            StatusCodes.OK
          }
        }
    }

}
