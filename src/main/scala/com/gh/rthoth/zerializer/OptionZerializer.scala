package com.gh.rthoth.zerializer

import java.io.{DataInput, DataOutput}

class OptionZerializer[T](zerializer: Zerializer[T]) extends Zerializer[Option[T]] {

  override def write(value: Option[T], output: DataOutput): Unit = {
    value match {
      case Some(v) =>
        output.writeByte('S')
        zerializer.write(v, output)

      case None =>
        output.writeByte('N')
        output.writeBoolean(false)
    }
  }

  override def read(input: DataInput): Option[T] = {
    input.readUnsignedByte() match {
      case 'S' => Some(zerializer.read(input))
      case 'N' => None
    }
  }
}
