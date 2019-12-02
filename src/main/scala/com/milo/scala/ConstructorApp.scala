package com.milo.scala

object ConstructorApp {

  def main(args: Array[String]): Unit = {
    val person2 = new Person2("zhangshan", 20)
    println(person2.name + " " + person2.age + " " + person2.school + " " + person2.gender)

    val yuan = new Person2("yuan", 28, "MALE")
    println(yuan.name + " " + yuan.age + " " + yuan.school + " " + yuan.gender)

  }
}

// 主构造函数
class Person2(var name: String, var age: Int) {
  println("enter ... ")

  val school = "hpu"
  var gender: String = _ //初始值是null

  def this(name: String, age: Int, gender: String) {
    this(name, age) // 附属构造器必须第一行调用主构造器 or其他附属构造器
    this.gender = gender
  }

  println("leave ... ")
}
