package uk.co.myspots.services

import spray.routing.HttpService

trait PingService extends HttpService {

  lazy val pingRoute =
    path("ping") {
      get {
        complete {
          "PONG!"
        }
      }
    }
}