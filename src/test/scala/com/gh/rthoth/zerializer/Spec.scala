package com.gh.rthoth.zerializer

import java.io.DataOutput

abstract class Spec {

  val listZerializer = iterableZerializer(IntZerializer, scala.collection.immutable.List)

  listZerializer.write(Nil, null : DataOutput)

}

