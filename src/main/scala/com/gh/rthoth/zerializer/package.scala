package com.gh.rthoth

import java.io.{DataInput, DataOutput}

package object zerializer {

  implicit object StringZerializer extends Zerializer[String] {

    override def write(value: String, output: DataOutput): Unit = {
      output.writeUTF(value)
    }

    override def read(input: DataInput): String = {
      input.readUTF()
    }
  }

}