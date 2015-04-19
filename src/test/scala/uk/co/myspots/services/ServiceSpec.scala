package uk.co.myspots.services

import org.scalatest.{ShouldMatchers, WordSpec}
import spray.testkit.ScalatestRouteTest

trait ServiceSpec extends WordSpec with ShouldMatchers with ScalatestRouteTest {

  def actorRefFactory = system
}