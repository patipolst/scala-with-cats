package com.github.patipolst

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._
import com.github.patipolst.PrintableInstances._
import com.github.patipolst.PrintableSyntax._

object Main extends App {
  val strValue = "abc"
  val intValue = 1

  // Interface object
  Printable.print(strValue)
  Printable.print(intValue)

  val cat = Cat("Garfield", 38, "ginger and black")
  Printable.print(cat)

  // Interface syntax
  strValue.print
  intValue.print
  cat.print

  // cats show
  implicit val catShow = Show.show[Cat] { cat =>
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show
    s"$name is a $age year-old $color cat."
  }

  println {
    Console.MAGENTA + cat.show + Console.RESET
  }
}