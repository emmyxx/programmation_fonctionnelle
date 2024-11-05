// Databricks notebook source
// MAGIC %md
// MAGIC ### Composable
// MAGIC - Combining simple functions to create a complex one
// MAGIC ``` 
// MAGIC f(x) = x*2 
// MAGIC g(x) = x+2
// MAGIC ```
// MAGIC - New function combining the two
// MAGIC ``` 
// MAGIC h(x) = f(g(x))
// MAGIC      =f(x+2)
// MAGIC      = (x+2)*2 
// MAGIC ```

// COMMAND ----------

def f(x: Int): Int = x * 2

def g(x: Int): Int = x + 2

def h(x: Int): Int = f(g(x))

// COMMAND ----------

val input = 4
println(s"g($input) = ${g(input)}")
println(s"f(g($input)) = ${f(g(input))}")
println(s"h($input) = ${h(input)}")

// COMMAND ----------

// MAGIC %md
// MAGIC ## Scala programmiung language

// COMMAND ----------

val name: String = "Scala"
val age: Int = 25

// COMMAND ----------

val language: Int = "Scala"
//because the val type is Int and not String

// COMMAND ----------

def add(x: Int, y: Int): Int = x + y
val sum = add(10,20)
println(s"Sum is $sum")

// COMMAND ----------

// MAGIC %md
// MAGIC ## Operators
// MAGIC

// COMMAND ----------

val x = 10
val y = 20
val z = x + y

// COMMAND ----------

val z = x.+ (y)

// COMMAND ----------

val z1 = x.*(y)

// COMMAND ----------

// MAGIC %md
// MAGIC ## Traits

// COMMAND ----------

trait Shape {
  def area(): Int
}

class Square(length: Int) extends Shape {
  def area = length * length
}

class Rectangle(length: Int, width: Int) extends Shape {
  def area = length * width
}

val square = new Square(10)
val area = square.area

// COMMAND ----------

// MAGIC %md
// MAGIC ## Tuples

// COMMAND ----------

val twoElements = ("10", true)
val threeElements = ("10", "Scout", true)

// COMMAND ----------

val first = threeElements._1
val second = threeElements._2
val third = threeElements._3

// COMMAND ----------

// MAGIC %md
// MAGIC ##Collections

// COMMAND ----------

// MAGIC %md
// MAGIC ###  Sequences

// COMMAND ----------

val arr = Array(10,20,30,40)

// COMMAND ----------

arr(0) = 50

// COMMAND ----------

val first = arr(0)

// COMMAND ----------

// MAGIC %md
// MAGIC ##List

// COMMAND ----------

val xs = List(10,20,30,40)

// COMMAND ----------

val ys = (1 to 100).toList

// COMMAND ----------

val zs = arr.toList

// COMMAND ----------

// MAGIC %md
// MAGIC

// COMMAND ----------

zs.tail

// COMMAND ----------

zs.isEmpty

// COMMAND ----------

// MAGIC %md
// MAGIC ## Vectors

// COMMAND ----------

val v1 = Vector(0,10,20,30,40)

// COMMAND ----------

val v2 = v1 :+50

// COMMAND ----------

val v3 = v2 :+60

// COMMAND ----------

val v4 = v3(4)

// COMMAND ----------

// MAGIC %md
// MAGIC ## Sets

// COMMAND ----------

val KC = Set("Cabochard", "Saken", "Caliste")

// COMMAND ----------

KC.contains("Saken")

// COMMAND ----------

KC.isEmpty

// COMMAND ----------

// MAGIC %md
// MAGIC ## Map

// COMMAND ----------

val tR = Map("Karmine Corp" -> "GOAT", "Vita" -> "HAH")

// COMMAND ----------

tR("Karmine Corp")

// COMMAND ----------

// MAGIC %md
// MAGIC ## Higher-Order Methods on Collection Classes

// COMMAND ----------

// MAGIC %md
// MAGIC ### Map

// COMMAND ----------

val myList = List(1,2,3,4)

// COMMAND ----------

val myAnotherList = myList.map(_ *10.0)

// COMMAND ----------

// MAGIC %md
// MAGIC ### Flatmap

// COMMAND ----------

val line = "Scala is fun"

// COMMAND ----------

val SingleSpace = " "

// COMMAND ----------

val words = line.split(SingleSpace)

// COMMAND ----------

val arrayOfListOfChars = words.map{_.toList}

// COMMAND ----------

val arrayOfChars = words.flatMap{_.toList}

// COMMAND ----------

// MAGIC %md
// MAGIC ### Filter

// COMMAND ----------

val myNewList = (1 to 100).toList

// COMMAND ----------

val even = myNewList.filter(_%2 == 0)

// COMMAND ----------

// MAGIC %md
// MAGIC ### Foreach

// COMMAND ----------

val words = "Scala is fun".split(" ")

// COMMAND ----------

words.foreach(println)

// COMMAND ----------

// MAGIC %md
// MAGIC ### Reduce

// COMMAND ----------

val reduceListe = List(2, 4, 6, 8, 10)

// COMMAND ----------

val sum = reduceList.reduce((x, y) => x + y)


// COMMAND ----------

val sum = reduceList.reduce((x, y) => x * y)
