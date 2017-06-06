package week4.frp

/**
  * Created by priyammitra on 6/6/17.
  */
class Var[T](expr: => T) extends Signal[T](expr) {
  def update(expr: => T): Unit = ???
}

object Var {
  def apply[T](expr: => T) = new Var(expr)
}
