val s = "mio testo #tag e poi #tag2 #pippo"

val k = s.split("\\s+").toList

val c = k.size

val l = k.filter(_.startsWith("#")).map(_.substring(1))





