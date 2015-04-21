package uk.co.myspots.services

import java.util.concurrent.TimeUnit

import akka.actor.ActorRef
import akka.util.Timeout
import spray.http.StatusCodes
import spray.json._
import spray.routing.HttpService
import uk.co.myspots.actors.{AddSpotToUser, GetAllSpots, GetUser, CreateUser}
import uk.co.myspots.model.{Spot, RestApiJsonProtocol, User}
import akka.pattern.ask
import scala.collection.immutable.Stream.Empty
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
          onSuccess(ask(userActor, GetAllSpots(username)).mapTo[Option[List[Spot]]]) {
            // todo: thinking about Spots Actor as child of User Actor
            case Some(l) => complete(l)
            case _ => complete(StatusCodes.NotFound)
          }

        } ~ post {
          entity(as[Spot]) { spot =>
            complete {
              userActor ! AddSpotToUser(username, spot)
              StatusCodes.Created
            }
          }
        }
      }

    }

}
