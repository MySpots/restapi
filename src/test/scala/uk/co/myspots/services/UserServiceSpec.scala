package uk.co.myspots.services

import uk.co.myspots.model.{RestApiJsonProtocol, User}
import RestApiJsonProtocol._
import uk.co.myspots.model.User


class UserServiceSpec extends ServiceSpec with UserService {

  val uberto = new User("Uberto", "Barbini", 0, "uberto")

  "user service" should {
    "return a 201 on a POST /user" in {

      Put("/user/uberto", uberto) ~> userRoute ~> check {
        status.intValue shouldBe 201
        //test redirect?
      }
    }
  }

}
