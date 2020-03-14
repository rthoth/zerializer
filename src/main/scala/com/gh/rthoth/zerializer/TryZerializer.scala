package com.gh.rthoth.zerializer

import java.io.{DataInput, DataOutput}

import scala.util.{Failure, Success, Try}

class TryZerializer[T](tZ: Zerializer[T], throwZ: Zerializer[Throwable]) extends Zerializer[Try[T]] {

  override def write(value: Try[T], output: DataOutput): Unit = {
    value match {
      case Success(content) =>
        output.writeByte('S')
        tZ.write(content, output)

      case Failure(throwable) =>
        output.writeByte('F')
        throwZ.write(throwable, output)
    }
  }

  override def read(input: DataInput): Try[T] = {
    input.readUnsignedByte() match {
      case 'S' => Success(tZ.read(input))
      case 'F' => Failure(throwZ.read(input))
    }
  }
}
