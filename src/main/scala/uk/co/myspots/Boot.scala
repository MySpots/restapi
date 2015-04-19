package uk.co.myspots

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.util.Timeout
import spray.can.Http
import akka.pattern.ask
import uk.co.myspots.actors.ApiActor
import scala.concurrent.duration._
import scala.language.postfixOps

object Boot {

  def main(args: Array[String]) {

    implicit val system = ActorSystem("myspots")
    implicit val timeout = Timeout(5 seconds)

    val api = system.actorOf(Props[ApiActor])

    IO(Http) ? Http.Bind(api, interface = "localhost", port = 8080)
  }

}
