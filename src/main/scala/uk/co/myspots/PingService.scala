package uk.co.myspots

import akka.actor.Actor
import spray.routing.HttpService


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
    }

}