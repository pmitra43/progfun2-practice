import week3.BankAccount

val acct = new BankAccount
acct deposit 50
acct withdraw 20
acct withdraw 20

val x = new BankAccount
val y = new BankAccount

x deposit(30)
x withdraw(20)

def WHILE(condition: => Boolean)(command: => Unit): Unit = {
  if(condition) {
    command
    WHILE(condition)(command)
  }
  else command
}

def REPEAT(command: => Unit)(condition: => Boolean): Unit = {
  command
  UNTIL(condition)
}

def UNTIL(condition: => Boolean) = {
  if(!condition) REPEAT()
}
