package com.gh.rthoth.zerializer

import java.time.Instant
import java.time.chrono.ChronoLocalDateTime
import java.time.temporal.ChronoUnit

import com.gh.rthoth.zerializer.CZerializerSpec.{Appointment, Super}

object CZerializerSpec {

  case class Appointment(start: Instant, person: String)

  case class Super(number: Int, string: String, instant1: Instant, instant2: Instant)

}

class CZerializerSpec extends Spec {

  implicit val instantZerializer: Zerializer[Instant] =
    mappedZerializer[Instant, String](i => i.toString, s => Instant.parse(s))

  "Appointment" in {
    val zerializer = newBuilder[Appointment]
      .field[Instant]("start")
      .field[String]("person")
      .mapTo(Appointment.apply, Appointment.unapply)

    val now = Instant.now()

    val input = writeToInputStream(zerializer, Appointment(now, "Einstein!"))
    zerializer.read(input) should be(Appointment(now, "Einstein!"))
  }

  "Super" in {
    val zerializer = newBuilder[Super]
      .field[Int]("number")
      .field[String]("string")
      .field[Instant]("i1")
      .field[Instant]("i2")
      .mapTo(Super.apply, Super.unapply)
    val now = Instant.now()
    val later = now.plus(10, ChronoUnit.DAYS)
    val input = writeToInputStream(zerializer, Super(1, "Hi!!", now, later))
    zerializer.read(input) should be(Super(1, "Hi!!", now, later))
  }

}
