package uk.co.myspots

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.util.Timeout
import spray.can.Http
import akka.pattern.ask
import scala.concurrent.duration._

object Boot {

  def main(args: Array[String]) {

    implicit val system = ActorSystem("myspots")
    implicit val timeout = Timeout(5 seconds)


    // we'll need to have a "route actor" that deals with all the different routings. the routes may be in different files.
    val service = system.actorOf(Props[PingServiceActor])

    IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
  }

}
