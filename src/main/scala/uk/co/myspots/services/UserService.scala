package uk.co.myspots.services

import java.util.concurrent.TimeUnit

import akka.actor.ActorRef
import akka.util.Timeout
import spray.http.StatusCodes
import spray.json._
import spray.routing.HttpService
import uk.co.myspots.actors._
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
        } ~ delete {
            complete{
              userActor ! DeleteUser(username)
                StatusCodes.NoContent
            }
        }
      } ~ pathPrefix("spots") {
        pathEnd {
          get {
            onSuccess(ask(userActor, GetAllSpots(username)).mapTo[Option[Map[String, Spot]]]) {
              // todo: thinking about Spots Actor as child of User Actor
              case Some(l) => complete(l)
              case _ => complete(StatusCodes.NotFound)
            }

          } ~ post {
            entity(as[Spot]) { spot =>

              onSuccess(ask(userActor, AddSpotToUser(username, spot)).mapTo[Option[String]]) {
                case Some(id) => redirect("/" + username + "/spots/" + id, StatusCodes.SeeOther )
                case _ => complete(StatusCodes.NotFound)
              }
            }
          }
        } ~ pathPrefix(Segment) { spotId =>
            delete {
              complete {
                userActor ! DeleteSpot(username, spotId)
                StatusCodes.NoContent
              }
            } ~ get {
              onSuccess(ask(userActor, GetSpot(username, spotId)).mapTo[Option[Spot]]) {
                case Some(s) => complete(s)
                case _ => complete(StatusCodes.NotFound)
              }
            }
          }
        }
      }

}
