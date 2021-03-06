package uk.co.myspots.actors

import java.util.concurrent.TimeUnit

import akka.actor.{Props, Actor}
import akka.util.Timeout
import uk.co.myspots.services.{PingService, UserService}

class ApiActor extends Actor with Api {

  override def actorRefFactory = context
  override def receive = runRoute(route)

}

trait Api extends PingService with UserService {

  val userActor = actorRefFactory.actorOf(Props[UserActor], "user-actor")

  def route = pingRoute ~ userRoute(userActor)
}