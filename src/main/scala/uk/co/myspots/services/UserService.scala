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
import scala.concurrent.ExecutionContext.Implicits.global


trait UserService extends HttpService {

  import RestApiJsonProtocol._

  implicit val timeout = Timeout(5, TimeUnit.SECONDS)


  def userRoute(userActor: ActorRef) =

    pathPrefix("user" / Segment) { username =>
      pathEnd {
        put {
          entity(as[User]) { user =>
            complete {
              userActor ! CreateUser(user)
              "Created user " + username + "  " + user.toJson
              StatusCodes.Created
            }
          }
        } ~ get {
          onSuccess(ask(userActor, GetUser(username)).mapTo[Option[User]]) {
            // getOrElse or fold don't work we would be using two different complete, but we shouldn't patmatch on Options
            case Some(user) => complete(user)
            case _ => complete(StatusCodes.NotFound)
          }
        }
      } ~ path("spots") {
        get {
          // complete( 500, "get all spots of user") //todo
          complete(StatusCodes.OK)
        } ~ post {
          complete(500, "add a spot to user ") //todo
        }
      }

    }

}
