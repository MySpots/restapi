package uk.co.myspots.services

class PingServiceSpec extends ServiceSpec with PingService {

  "ping service" should {
    "return a pong on a GET /ping" in {
      Get("/ping") ~> pingRoute ~> check {
        responseAs[String] shouldBe "PONG!"
      }
    }
  }
}
