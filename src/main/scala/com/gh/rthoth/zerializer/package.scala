package com.gh.rthoth

import java.io.{DataInput, DataOutput}

import scala.collection.immutable.HashSet
import scala.collection.{IterableFactory, mutable}
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

  implicit def optionZerializer[T](implicit zerializer: Zerializer[T]): OptionZerializer[T] = {
    new OptionZerializer(zerializer)
  }

  implicit def eitherZerializer[L, R](implicit lZ: Zerializer[L], rZ: Zerializer[R]): EitherZerializer[L, R] = {
    new EitherZerializer(lZ, rZ)
  }

  implicit def throwableZerializer: Zerializer[Throwable] = ThrowableZerializer

  implicit def iterableZerializer[T, C[_] <: Iterable[_]](implicit zerializer: Zerializer[T], factory: IterableFactory[C]): Zerializer[C[T]] = {
    new IterableZerializer(factory, zerializer)
  }

}