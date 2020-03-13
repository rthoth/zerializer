package com.gh.rthoth.zerializer

import java.io.{DataInput, DataOutput}

class OptionZerializer[T](zerializer: Zerializer[T]) extends Zerializer[Option[T]] {

  override def write(value: Option[T], output: DataOutput): Unit = {
    value match {
      case Some(v) =>
        output.writeBoolean(true)
        zerializer.write(v, output)

      case None =>
        output.writeBoolean(false)
    }
  }

  override def read(input: DataInput): Option[T] = {
    if (input.readBoolean()) {
      Some(zerializer.read(input))
    } else {
      None
    }
  }
}
