package com.github.patipolst

case class Cat(name: String, age: Int, color: String)

trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val stringPrintable: Printable[String] = new Printable[String] {
    override def format(value: String): String = value
  }

  // can be written as below
  implicit val intPrintable: Printable[Int] = (value: Int) => value.toString

  implicit val catPrintable: Printable[Cat] = (cat: Cat) => {
    val name = Printable.format(cat.name)
    val age = Printable.format(cat.age)
    val color = Printable.format(cat.color)
    s"$name is a $age year-old $color cat."
  }
}

object Printable {
  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit = println {
    Console.CYAN + format(value) + Console.RESET
  }
}

object PrintableSyntax {

  implicit class PrintableOps[A](value: A) {
    def format(implicit printable: Printable[A]): String = printable.format(value)

    def print(implicit printable: Printable[A]): Unit = println {
      Console.CYAN + format(printable) + Console.RESET
    }
  }

}
