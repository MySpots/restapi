package uk.co.myspots

import org.scalatest._
import spray.testkit.ScalatestRouteTest

/**
 * Created by uberto on 16/04/15.
 */
class UserServiceSpec extends WordSpec with ScalatestRouteTest with PingService with ShouldMatchers {

    def actorRefFactory = system // connect the DSL to the test ActorSystem

    "user service" should {
      "return a 201 on a POST /user" in {
        Put("/user/uberto") ~> route ~> check {
          status.intValue shouldBe 201
          //test redirect?
        }
      }
    }

}
