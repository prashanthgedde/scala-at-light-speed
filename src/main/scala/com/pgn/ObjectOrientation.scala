package com.pgn

/*
Concepts:
  class, object, trait, abstract class
  apply method
  companion object
  case class
  Immutable objects
 */

object ObjectOrientation extends App {

  // class and instance
  class Animal {
    val age: Int = 0
    def eat() = println("I'm eating")
  }
  val anAnimal = new Animal

  // inheritance
  //class Dog extends Animal

  class Dog(val name: String) extends Animal

  val aDog = new Dog("tommy")
  aDog.name

  // abstract class
  abstract class WalkingAnimal {
    val hasLegs = true
    def walk(): Unit
  }

  //val aWalkingAnimal = new WalkingAnimal

  // interface = ultimate abstract class - everything unimplemented
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought: String) : Unit
  }

  // single class inheritance + multi-trait - "mixing"
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println(s"I'm eating ${animal}")
    override def ?!(thought: String): Unit = println(s"I'm thinking ${thought}")
  }

  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog //infix notation  = object method argument => only available for methods with one argument.

  aCroc ?! "I can fly"

  // Anonymous class
  val aDinosaur = new Carnivore {
    override def eat(animal: Animal) : Unit = println("I'm a dino and can eat anything")
  }

  aDinosaur eat aCroc

  object MySingleton { // the only singleton of MySingleton type
    val mySpecialVal = 32324
    def mySpecialMethod(): Int = 42
    def apply(x: Int): Int = x+1
  }

  MySingleton.mySpecialMethod()
  MySingleton.apply(65)
  println("MySingleton(65): "+MySingleton(65)) // equivalent to MySingleton.apply(65)

  object Animal {
    //companion object for Animal class.
    // Companion can access into each others' private members.

    val canLiveIndefinitely = false
  }

  /*
  case class = lightweight data structure with some boilerplate
  Auto generates:
    -  sensible equals anf hash code
    - serialization
    - companion with apply
  */
  case class Person(name: String, age: Int)

  // Since case class comes with apply, we can instantiate a Person class without `new`.
  val bob = Person("Bob", 54)


  // exception
  try {
    // code that can throw
    val x: String = null
    x.length
  }
  catch {
    case e: Exception => println(s"Some error message: $e")
  }
  finally {
    // execute sme code no matter what
  }

  // generics
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  // using a generic with a concrete type
  val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head
  val rest = aList.tail

  val aStrList: List[String] = List("Hello", "Scala")
  val firstString = aStrList.head


  //1. In Scala, we operate with immutable values/objects
  // Any modification to an object must returns another instance.
  // Helps to scale multi-threaded programs
  val reversedList = aList.reverse // returns a new list.

  //2. Scala is closest the OO idea
}
