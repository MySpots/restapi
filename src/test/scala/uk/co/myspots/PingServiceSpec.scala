package uk.co.myspots

import org.scalatest.{ShouldMatchers, WordSpec}
import spray.testkit.ScalatestRouteTest

class PingServiceSpec extends RestApiRouteSpec with PingService {

  def actorRefFactory = system // connect the DSL to the test ActorSystem

  "ping service" should {
    "return a pong on a GET /ping" in {
      Get("/ping") ~> route ~> check {
        responseAs[String] shouldBe "PONG!"
      }
    }
  }
}
