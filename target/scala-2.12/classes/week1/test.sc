val f: PartialFunction[String, String] = {
  case "ping" => "pong"
}

f.isDefinedAt("ping")
f.isDefinedAt("abc")

val e1 = List(1, 2, 3, 4, 5)


