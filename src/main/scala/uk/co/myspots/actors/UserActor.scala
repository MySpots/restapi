package uk.co.myspots.actors

import java.util.concurrent.TimeUnit

import akka.actor.Actor
import akka.util.Timeout
import uk.co.myspots.model.User

class UserActor extends Actor {

  var users = Set.empty[User]

  override def receive = {
    case CreateUser( user) =>
      users = users + user
    case GetUser(username) =>
      users.find( u => u.userId == username)
  }

}
