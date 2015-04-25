package uk.co.myspots.model

import java.security.MessageDigest

object Md5 {

  val digest = MessageDigest.getInstance("MD5")

  def md5hash1(text:String):String = digest.digest(text.getBytes).map("%02x".format(_)).mkString

}
