package uk.co.myspots.actors

import uk.co.myspots.model.User


trait UserActorMessage

case class CreateUser(user: User) extends UserActorMessage

case class GetUser(username: String) extends UserActorMessage