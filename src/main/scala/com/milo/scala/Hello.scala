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
    //    println(content.mkString) // 懒加载，用到变量的时候才加载

    println(add(4, 5)) // 9
    println(three) // 没有入参的函数调用时可以省略括号
    sayHi
    sayName("milo")
    sayName()
    println(sum(1, 2, 3, 4))
    //
    println("=== for循环 ===")
    for (i <- 1 to 10 /* if i % 2 == 0 只对偶数做打印*/ ) {
      println(i) // 1 2 ... 10
    }

    var courses = Array("hadoop", "spark", "java web")
    for (course <- courses) {
      println(course)
    }
    // course 就是courses中的每一个元素
    courses.foreach(course => println(course))

    var (num, mysum) = (100, 0)
    while (num > 0) {
      mysum = num + mysum
      num = num - 1
    }
    println(mysum)

    val range = Range(1,10,1) // [1,10) 步长1
    range.foreach(r => println(r))
    val range2 = 1.to(10) // [1,10]
  }

  // 最后一行就是返回值 不需要return
  def add(x: Int, y: Int): Int = {
    x + y
  }

  def three() = 1 + 2

  // unit means no return
  def sayHi(): Unit = {
    println("hello moto")
  }

  // 默认参数,没传入参数时就用默认参数
  def sayName(name: String = "default name") = {
    println("hello " + name)
  }

  // 可变入参，传入多个数
  def sum(numbers: Int*) = {
    var res = 0
    for (number <- numbers) {
      res += number
    }
    res
  }
}
