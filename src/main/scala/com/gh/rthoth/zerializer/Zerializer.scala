package com.gh.rthoth.zerializer

import java.io.{DataInput, DataOutput}

trait Zerializer[T] {

  def write(value: T, output: DataOutput): Unit

  def read(input: DataInput): T
}
