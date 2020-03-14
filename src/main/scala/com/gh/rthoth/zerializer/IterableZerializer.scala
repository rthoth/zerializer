package com.gh.rthoth.zerializer

import java.io.{DataInput, DataOutput}

import scala.collection.IterableFactory

class IterableZerializer[E, C[E] <: Iterable[E]](zerializer: Zerializer[E], factory: IterableFactory[C], limit: Int) extends Zerializer[C[E]] {

  override def write(value: C[E], output: DataOutput): Unit = {
    output.writeInt(value.size)
    for (e <- value)
      zerializer.write(e, output)
  }

  override def read(input: DataInput): C[E] = {
    val size = input.readInt()
    if (size > 0 && size <= limit) {
      val builder = factory.newBuilder[E]
      for (_ <- 0 until size)
        builder += zerializer.read(input)

      builder.result()
    } else if (size == 0) {
      factory.empty
    } else if (size > limit) {
      throw new ZerializerException(s"Invalid size [$size], it was greater than [$limit]!")
    } else {
      throw new ZerializerException(s"Invalid size [$size]!")
    }
  }
}
