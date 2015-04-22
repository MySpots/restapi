package uk.co.myspots.actors

import akka.actor.Actor
import uk.co.myspots.model.{Spot, User}

class UserActor extends Actor {

  var users = Set.empty[User]

  var spots = Map.empty[String, List[Spot]]

  override def receive = {
    case CreateUser(user) =>
      users = users + user
      spots += user.userId -> List()
    case GetUser(username) =>
      sender ! users.find(_.userId == username)
    case GetAllSpots(username) =>
      sender ! spots.get(username)
    case AddSpotToUser(userId, spot) =>
      users.find(_.userId == userId).foreach { _ =>
        spots += userId -> (spot :: spots(userId))
      }

  }

}
