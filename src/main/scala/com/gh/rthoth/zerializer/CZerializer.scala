package com.gh.rthoth.zerializer

object CZerializer {

  case class Field[T](name: String, zerializer: Zerializer[T])

}

abstract class CZerializer[T] extends Zerializer[T] {

}
