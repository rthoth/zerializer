package com.gh.rthoth.zerializer

import java.io.{DataInput, DataOutput, InputStream, ObjectInputStream, ObjectOutputStream, OutputStream}

import com.gh.rthoth.zerializer.io.{ToInputStream, ToOutputStream}

object ThrowableZerializer extends Zerializer[Throwable] {

  override def write(value: Throwable, output: DataOutput): Unit = {
    new ObjectOutputStream(new ToOutputStream(output)).writeObject(value)
  }


  override def write(value: Throwable, output: OutputStream): Unit = {
    new ObjectOutputStream(output).writeObject(value)
  }

  override def read(input: DataInput): Throwable = {
    new ObjectInputStream(new ToInputStream(input)).readObject() match {
      case throwable: Throwable => throwable
      case other =>
        throw new ZerializerException(s"Invalid object class [${other.getClass.getName}]!")
    }
  }

  override def read(input: InputStream): Throwable = {
    new ObjectInputStream(input).readObject() match {
      case throwable: Throwable => throwable
      case other =>
        throw new ZerializerException(s"Invalid object class [${other.getClass.getName}]!")
    }
  }
}
