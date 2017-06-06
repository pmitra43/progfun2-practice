package week4.Signals


class BankAccount {
  val balance = Var(0)

  def currentBalance = balance

  def deposit(amount: Int) =
    if (amount > 0) {
      val b = balance()
      balance() = b + amount
    }

  def withdraw(amount: Int) =
    if (0 < amount && amount <= balance()) {
      val b = balance()
      balance() = b - amount
    } else throw new Error("Insufficient funds")
}