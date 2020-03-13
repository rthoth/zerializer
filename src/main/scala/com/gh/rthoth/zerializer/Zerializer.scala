package com.gh.rthoth.zerializer

import java.io.{DataInput, DataInputStream, DataOutput, DataOutputStream, InputStream, OutputStream}

trait Zerializer[T] {

  def write(value: T, output: DataOutput): Unit

  def read(input: DataInput): T

  def write(value: T, output: OutputStream): Unit = {
    write(value, new DataOutputStream(output): DataOutput)
  }

  def read(input: InputStream): T = {
    read(new DataInputStream(input): DataInput)
  }
}
