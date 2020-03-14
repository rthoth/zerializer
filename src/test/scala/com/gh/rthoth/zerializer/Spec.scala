package com.gh.rthoth.zerializer

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, InputStream}

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

abstract class Spec extends AnyFreeSpec with Matchers {

  protected def writeToInputStream[T](zerializer: Zerializer[T], value: T): InputStream = {
    val output = new ByteArrayOutputStream()
    zerializer.write(value, output)
    new ByteArrayInputStream(output.toByteArray)
  }
}

