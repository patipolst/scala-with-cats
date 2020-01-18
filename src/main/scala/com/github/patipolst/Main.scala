package com.github.patipolst

import java.util.Date

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

  import cats.Show
  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.show._

  implicit val catShow = Show.show[Cat] { cat =>
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show
    s"$name is a $age year-old $color cat."
  }

  println {
    Console.MAGENTA + cat.show + Console.RESET
  }

  // cats eq

  import cats.Eq
  import cats.instances.long._
  import cats.syntax.eq._ // for Eq

  implicit val dateEq: Eq[Date] =
    Eq.instance[Date] { (date1, date2) =>
      date1.getTime === date2.getTime
    }

  val x = new Date() // now
  val y = new Date() // a bit later than now

  println {
    Console.CYAN + "x === x: " + (x === x) + Console.RESET
  }

  implicit val catEqual: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>
      (cat1.name === cat2.name) &&
        (cat1.age === cat2.age) &&
        (cat1.color === cat2.color)
    }

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  println {
    Console.MAGENTA + "cat1 === cat1: " + (cat1 === cat1) + Console.RESET
  }
  println {
    Console.MAGENTA + "cat1 === cat2: " + (cat1 === cat2) + Console.RESET
  }
  println {
    Console.MAGENTA + "cat1 =!= cat2: " + (cat1 =!= cat2) + Console.RESET
  }


  import cats.instances.option._

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  println {
    Console.MAGENTA + "optionCat1 === optionCat1: " + (optionCat1 === optionCat1) + Console.RESET
  }
  println {
    Console.MAGENTA + "optionCat1 === optionCat2: " + (optionCat1 === optionCat2) + Console.RESET
  }
  println {
    Console.MAGENTA + "optionCat1 =!= optionCat2: " + (optionCat1 =!= optionCat2) + Console.RESET
  }
}