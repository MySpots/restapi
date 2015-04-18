package uk.co.myspots

import spray.http._
import spray.json._
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import RestApiJsonProtocol._


class UserServiceSpec extends RestApiRouteSpec with PingService {

    def actorRefFactory = system // connect the DSL to the test ActorSystem

   val uberto = new User("Uberto", "Barbini", 0, "uberto")




  "user service" should {
      "return a 201 on a POST /user" in {


        //according to doc Put("/user/uberto", uberto) should work but it doesn't

        Put("/user/uberto",  HttpEntity(MediaTypes.`application/json`,
          uberto.toJson.compactPrint) ) ~> route ~> check {
          status.intValue shouldBe 201
          //test redirect?
        }
      }
    }

}
