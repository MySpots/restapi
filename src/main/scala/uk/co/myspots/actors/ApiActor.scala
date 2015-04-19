package uk.co.myspots.actors

import akka.actor.Actor
import uk.co.myspots.services.{PingService, UserService}

class ApiActor extends Actor with Api {

  override def actorRefFactory = context
  override def receive = runRoute(route)
}

trait Api extends PingService with UserService {

  def route = pingRoute ~ userRoute

}