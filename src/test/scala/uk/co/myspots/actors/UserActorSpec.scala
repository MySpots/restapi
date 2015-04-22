package uk.co.myspots.actors

import akka.actor.ActorSystem
import akka.testkit.{TestActorRef, TestKit}
import org.scalatest.{BeforeAndAfterAll, ShouldMatchers, WordSpecLike}
import uk.co.myspots.model.{Spot, User}

class UserActorSpec extends TestKit(ActorSystem("testsystem")) with WordSpecLike with ShouldMatchers with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "an User Actor" when {

    "receiving an AddSpotToUser message for an existing user" should {

      "add a spot to the user spots" in {

        val userActor = TestActorRef[UserActor]
        val anUser = User("Maccio", "Capatonda", 0, "maccio")
        userActor ! CreateUser(anUser)

        val aSpot = Spot("link", "title", 0, "maccio", 0, "test", List.empty)
        userActor ! AddSpotToUser(anUser.userId, aSpot)

        val spots = userActor.underlyingActor.spots
        spots should contain only anUser.userId -> List(aSpot)
      }

      "not add a spot if the user hasn't been created" in {

        val userActor = TestActorRef[UserActor]

        val aSpot = Spot("link", "title", 0, "maccio", 0, "test", List.empty)
        userActor ! AddSpotToUser("maccio", aSpot)

        val spots = userActor.underlyingActor.spots
        spots shouldBe 'empty
      }
    }
  }

}
