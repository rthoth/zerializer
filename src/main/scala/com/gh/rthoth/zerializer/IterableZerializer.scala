package com.gh.rthoth.zerializer

import java.io.{DataInput, DataOutput}

import scala.collection.IterableFactory

class IterableZerializer[T, C[_] <: Iterable[T]](factory: IterableFactory[C], zerializer: Zerializer[T]) extends Zerializer[C[T]] {

  override def write(value: C[T], output: DataOutput): Unit = {
    val size = value.size
    output.writeInt(size)

    for (elem <- value)
      zerializer.write(elem, output)
  }

  override def read(input: DataInput): C[T] = {
    val size = input.readInt()
    if (size > 0) {
      val builder = factory.newBuilder[T]
      for (_ <- 0 until size)
        builder += zerializer.read(input)
      builder.result()
    } else if (size == 0) {
      factory.empty
    } else {
      throw new ZerializerException(s"Invalid Iterable Size [$size]!")
    }
  }
}
