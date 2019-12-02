package com.milo.scala

object SimpleObject {

  def main(args: Array[String]): Unit = {
    val person = new Person()
    person.name = "kafka"
    println(person.age)
    println(person.eat)
    person.watchFootball("USA")

//    println(person.gender) 编译不过
    person.printInfo()
  }


}

class Person {
  var age = 10
  var name:String = _ // 占位符(var才可以用占位符) 按类型给默认值

  // private和 private[this] 有一点点区别
  private [this] val gender = "male"

  def printInfo() : Unit = {
    println("gender :" + gender)
  }

  def eat():String = {
    name + "is eating ... "
  }

  def watchFootball(team: String):Unit = {
    println(name  + " is watching match of " + team)
  }
}
