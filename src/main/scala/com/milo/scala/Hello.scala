package com.milo.scala

import scala.io.Source._

object Hello {

  def main(args: Array[String]): Unit = {
    println("hello world!")

    val money = 100 // 不可变
    var mon = 200 // 可变
    var d: Double = 1.1 // 默认double类型
    var f: Float = 1.2f
    val dd = 10.asInstanceOf[Double] // int转成double
    var isDouble = 10.isInstanceOf[Double] // 判断类型
    lazy val content = fromFile("D:\\2019project\\spark5\\pom.xml")
    println(content.mkString) // 懒加载，用到变量的时候才加载
  }
}
