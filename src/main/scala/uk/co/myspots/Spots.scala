package uk.co.myspots

import java.time.Instant

case class Spot(link: String, title: String, created: Instant, user: User, lastAccess: Instant, shortUrl: String, tags: List[String])

case class User(firstName: String, lastName: String, created: Instant, userId: String)

