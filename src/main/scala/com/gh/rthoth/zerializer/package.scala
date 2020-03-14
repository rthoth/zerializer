package com.gh.rthoth

import java.io.{DataInput, DataOutput}

import scala.collection.IterableFactory
import scala.language.implicitConversions

package object zerializer {

  implicit object IntZerializer extends Zerializer[Int] {

    override def write(value: Int, output: DataOutput): Unit = output.writeInt(value)

    override def read(input: DataInput): Int = input.readInt()
  }

  implicit object LongZerializer extends Zerializer[Long] {

    override def write(value: Long, output: DataOutput): Unit = output.writeLong(value)

    override def read(input: DataInput): Long = input.readLong()
  }

  implicit object StringZerializer extends Zerializer[String] {

    override def write(value: String, output: DataOutput): Unit = {
      output.writeUTF(value)
    }

    override def read(input: DataInput): String = {
      input.readUTF()
    }
  }

  def newBuilder[T]: CBuilder[T] = new CBuilder[T]

  implicit def eitherZerializer[L, R](implicit lZ: Zerializer[L], rZ: Zerializer[R]): EitherZerializer[L, R] = {
    new EitherZerializer(lZ, rZ)
  }

  implicit def iterableFactory[E, C[E] <: Iterable[E]](zerializer: Zerializer[E], factory: IterableFactory[C], limit: Int = 10000): Zerializer[C[E]] = {
    new IterableZerializer(zerializer, factory, limit)
  }

  implicit def mappedZerializer[I, O](to: I => O, from: O => I)(implicit zerializer: Zerializer[O]): Zerializer[I] = {
    new MappedZerializer(to, from, zerializer)
  }

  implicit def optionZerializer[T](implicit zerializer: Zerializer[T]): OptionZerializer[T] = {
    new OptionZerializer(zerializer)
  }

  implicit def throwableZerializer: Zerializer[Throwable] = ThrowableZerializer
}