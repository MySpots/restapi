package uk.co.myspots.actors

import akka.actor.Actor
import uk.co.myspots.model.User

class UserActor extends Actor {

  var users = Set.empty[User]

  override def receive = {
    case CreateUser(user) =>
      users = users + user
    case GetUser(username) =>
      sender ! users.find(_.userId == username)
  }

}
