package uk.co.myspots.model

import akka.testkit.TestActorRef
import org.scalatest._
import spray.testkit.ScalatestRouteTest
import uk.co.myspots.actors.{AddSpotToUser, CreateUser, UserActor}

/**
 * Created by uberto on 25/04/15.
 */
class SpotSpec extends WordSpec with ShouldMatchers {


  "a Spot" when {

    "asked for a id" should {

      "create id from link MD5" in {

        val aSpot = Spot("link", "title", 0, "maccio", 0)
        val id = aSpot.id
        id shouldBe "88fdd8"
      }
    }

    "created" should {
      "populate tag collection" in {

        val aSpot = Spot("link", "title with #tag1 and #tag2", 0, "uberto", 0)

        aSpot.tags.size shouldBe 2
        aSpot.tags shouldBe List("tag1","tag2")

      }
    }
  }
}
