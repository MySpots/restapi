package uk.co.myspots.services

import akka.testkit.TestActorRef
import uk.co.myspots.actors.UserActor
import uk.co.myspots.model.{RestApiJsonProtocol, User}
import RestApiJsonProtocol._
import uk.co.myspots.model.User


class UserServiceSpec extends ServiceSpec with UserService {

  val uberto = User("Uberto", "Barbini", 0, "uberto")

  val userActor = TestActorRef[UserActor]

  "user service" should {
    "return a 201 on a PUT /user/$username" in {

      Put("/user/uberto", uberto) ~> userRoute(userActor) ~> check {
        status.intValue shouldBe 201   //to be pedantic 201 should be for a new user, 204 for an update of existing user

        // see http://doc.akka.io/docs/akka/snapshot/scala/testing.html - maybe here we should only test that the message is sent?
        userActor.underlyingActor.users should contain only uberto
        //test redirect?
      }
    }

     "return a user json on a GET /user/$username" in {

      Put("/user/uberto", uberto) ~> userRoute(userActor)

      Get("/user/uberto", uberto) ~> userRoute(userActor) ~> check {
        responseAs[String] shouldBe "OK"

        //verify the json content
      }
    }
  }


}
