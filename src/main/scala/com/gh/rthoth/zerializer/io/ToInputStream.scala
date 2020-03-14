package com.gh.rthoth.zerializer.io

import java.io.{DataInput, EOFException, InputStream}

class ToInputStream(input: DataInput) extends InputStream {

  override def read(): Int = try {
    input.readUnsignedByte()
  } catch {
    case _: EOFException => -1
  }

}
