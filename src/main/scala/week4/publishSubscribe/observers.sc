import week4.publishSubscribe.{BankAccount, Consolidator}

object observers {
  println("Welcome to worksheet")
  val a, b = new BankAccount
  val c = new Consolidator(List(a, b))
  c.totalBalance
  a deposit 20
  c.totalBalance
  b deposit(30)
  c.totalBalance
}