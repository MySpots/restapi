package uk.co.myspots

class UserServiceSpec extends RestApiRouteSpec with PingService {

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
