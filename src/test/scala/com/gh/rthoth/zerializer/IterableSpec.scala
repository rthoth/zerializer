package com.gh.rthoth.zerializer

import scala.collection.mutable

class IterableSpec extends Spec {

  "Set[String]" in {
    val zerializer = iterableFactory(StringZerializer, Set)
    val input = writeToInputStream(zerializer, Set("Hello!", "I'm Batman!", "Let's go away!"))
    zerializer.read(input) should contain only("Hello!", "I'm Batman!", "Let's go away!")
  }

  "Vector[Int]" in {
    val zerializer = iterableFactory(IntZerializer, Vector)
    val input = writeToInputStream(zerializer, Vector(1, 2, 10, 99, -100))
    zerializer.read(input) should contain inOrderOnly(1, 2, 10, 99, -100)
  }

  "immutable.Seq[String]" in {
    val zerializer = iterableFactory(StringZerializer, Seq)
    val input = writeToInputStream(zerializer, Seq("1", "44", "3343"))
    zerializer.read(input) should contain inOrderOnly("1", "44", "3343")
  }

  "mutable.Seq[Int]" in {
    val zerializer = iterableFactory(IntZerializer, mutable.Seq)
    val input = writeToInputStream(zerializer, mutable.Seq(1, 0, -77, 99))
    zerializer.read(input) should contain inOrderOnly(1, 0, -77, 99)
  }
}
