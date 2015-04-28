package uk.co.myspots.actors

import uk.co.myspots.model._

trait UserActorMessage

case class CreateUser(user: User) extends UserActorMessage
case class GetUser(username: String) extends UserActorMessage
case class DeleteUser(username: String) extends UserActorMessage

case class GetAllSpots(username: String) extends UserActorMessage
case class AddSpotToUser(username: String, spot: Spot) extends UserActorMessage
case class GetSpot(username: String, spotId: String) extends UserActorMessage
case class DeleteSpot(username: String, spotId: String) extends UserActorMessage

