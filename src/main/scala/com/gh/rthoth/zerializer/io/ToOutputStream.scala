package com.gh.rthoth.zerializer.io

import java.io.{DataOutput, OutputStream}

class ToOutputStream(output: DataOutput) extends OutputStream {

  override def write(byte: Int): Unit = output.write(byte)

  override def write(bytes: Array[Byte]): Unit = output.write(bytes)

  override def write(bytes: Array[Byte], off: Int, len: Int): Unit = output.write(bytes, off, len)
}
