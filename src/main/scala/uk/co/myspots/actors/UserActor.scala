package uk.co.myspots.actors

import akka.actor.Actor
import uk.co.myspots.model.{Spot, User}

class UserActor extends Actor {

  var users = Set.empty[User]

  var spots = Map.empty[String, Map[String, Spot]]

  override def receive = {
    case CreateUser(user) =>
      users += user
      spots += user.userId -> Map.empty[String, Spot]
    case GetUser(username) =>
      sender ! users.find(_.userId == username)
    case DeleteUser(username) =>
      users.find(_.userId == username).foreach(users -= _)
    case GetAllSpots(username) =>
      sender ! spots.get(username)
    case AddSpotToUser(username, spot) => {
      if (spots.contains(username)) {
        val spotId = spot.id
        spots += username -> (spots(username) + (spotId -> spot))
        sender ! Some(spotId)
      } else
        sender ! None
    }
    case GetSpot(username, spotId) =>
      sender ! spots.get(username).flatMap(_.get(spotId))
    case DeleteSpot(username, spotId) => {
      if (spots.contains(username) && spots(username).contains(spotId)) {
        spots += username -> (spots(username) - spotId)
        sender ! Some(spotId)
      } else
        sender ! None
      }
  }

}
