package uk.co.myspots

import akka.actor.Actor
import spray.http.{StatusCodes, HttpEntity, HttpCharsets, MediaTypes}
import spray.httpx.unmarshalling.Unmarshaller
import spray.routing.{RequestContext, HttpService}
//import spray.json._
//import DefaultJsonProtocol._ // if you don't supply your own Protocol (see below)


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
//        entity(Unmarshaller(MediaTypes.`application/json`) {
//          case httpEntity: HttpEntity => new User()
//            read[User](httpEntity.asString(HttpCharsets.`UTF-8`))
//        }) {
          complete{ "Created user " + userName

          StatusCodes.Created
          }
         // StatusCodes.Created
              //user: User =>
             //  ctx: RequestContext =>
            //  handleRequest(ctx, StatusCodes.Created) {
              //  log.debug("Creating user: %s".format(user))
              //  customerService.create(customer)
//              }
        }
      }


}