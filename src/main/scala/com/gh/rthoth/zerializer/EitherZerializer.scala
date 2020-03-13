package com.gh.rthoth.zerializer

import java.io.{DataInput, DataOutput}

class EitherZerializer[L, R](lZ: Zerializer[L], rZ: Zerializer[R]) extends Zerializer[Either[L, R]] {

  override def write(value: Either[L, R], output: DataOutput): Unit = {
    value match {
      case Left(l) =>
        output.writeChar('L')
        lZ.write(l, output)

      case Right(r) =>
        output.writeChar('R')
        rZ.write(r, output)
    }
  }

  override def read(input: DataInput): Either[L, R] = {
    input.readChar() match {
      case 'L' => Left(lZ.read(input))
      case 'R' => Right(rZ.read(input))
      case char => throw new ZerializerException(s"Invalid character [$char]!")
    }
  }
}
