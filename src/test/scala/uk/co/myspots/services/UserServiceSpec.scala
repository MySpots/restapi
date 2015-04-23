package uk.co.myspots.services

import akka.testkit.TestActorRef
import spray.http.StatusCodes
import uk.co.myspots.actors.UserActor
import uk.co.myspots.model.RestApiJsonProtocol._
import uk.co.myspots.model.{Spot, RestApiJsonProtocol, User}

class UserServiceSpec extends ServiceSpec with UserService {

  val uberto = User("Uberto", "Barbini", 0, "uberto")

  val google = Spot("http://www.google.com", "google", 0, "", 0, "", List())
  val facebook = Spot("http://www.facebook.com", "fb", 0, "", 0, "", List())

  val userActor = TestActorRef[UserActor]

  "user service" when {
    "creating a user" should {
      "return a 201 on a PUT /user/$username" in {

        Put("/user/uberto", uberto) ~> userRoute(userActor) ~> check {
          status shouldBe StatusCodes.Created //to be pedantic 201 should be for a new user, 204 for an update of existing user

          // see http://doc.akka.io/docs/akka/snapshot/scala/testing.html - maybe here we should only test that the message is sent?
          userActor.underlyingActor.users should contain only uberto
          //test redirect?
        }
      }
    }

    "retrieving a user" should {

      "return a 200 with user json on a GET /user/$username" in {

        Put("/user/uberto", uberto) ~> userRoute(userActor)

        Get("/user/uberto") ~> userRoute(userActor) ~> check {
          responseAs[User] shouldBe uberto
        }
      }

      "return 404 when the user is not found" in {

        Get("/user/maccio") ~> userRoute(userActor) ~> check {
          status shouldBe StatusCodes.NotFound
        }
      }

      "return list of spots when retriving the spots of a user" in {

        Put("/user/uberto", uberto) ~> userRoute(userActor)
        Post("/user/uberto/spots", google) ~> userRoute(userActor)
        Post("/user/uberto/spots", facebook) ~> userRoute(userActor)

        Get("/user/uberto/spots") ~> userRoute(userActor) ~> check {
          responseAs[List[Spot]] shouldBe List(facebook, google)
        }
      }

      "return 404 when retriving the spots of a user not found" in {

       Get("/user/maccio/spots") ~> userRoute(userActor) ~> check {
         status shouldBe StatusCodes.NotFound
        }
      }
    }
  }


}
