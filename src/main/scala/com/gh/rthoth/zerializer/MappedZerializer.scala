package com.gh.rthoth.zerializer

import java.io.{DataInput, DataOutput}

class MappedZerializer[I, O](to: I => O, from: O => I, zerializer: Zerializer[O]) extends Zerializer[I] {

  override def write(value: I, output: DataOutput): Unit = {
    zerializer.write(to(value), output)
  }

  override def read(input: DataInput): I = {
    from(zerializer.read(input))
  }
}
