package uk.co.myspots.services

import spray.http.StatusCodes
import spray.json._
import spray.routing.HttpService
import uk.co.myspots.model.{RestApiJsonProtocol, User}

trait UserService extends HttpService {

  import RestApiJsonProtocol._

  lazy val userRoute =
    path("user" / Segment) { username =>
      put {
        entity(as[User]) { user => //extract json Bar from post body
          complete {
            "Created user " + username + "  " + user.toJson
            StatusCodes.Created
          }
        }
      }
    }

}
