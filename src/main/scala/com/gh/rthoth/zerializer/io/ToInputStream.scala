package com.gh.rthoth.zerializer.io

import java.io.{DataInput, InputStream}

class ToInputStream(input: DataInput) extends InputStream {

  override def read(): Int = input.readChar()

}
