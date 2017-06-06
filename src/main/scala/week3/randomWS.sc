
def combineFunction(combineOp: (Int, Int) => Int, acc: Int)(f: Int=> Int)(a: Int, b: Int): Int = {
  if(a > b) acc else combineFunction(combineOp, combineOp(f(a), acc))(f)(a+1,b)
}

def add = (a: Int, b: Int) => a + b
def multiply = (a: Int, b: Int) => a * b

def square = (a: Int) => a * a
def cube = (a: Int) => a * a * a

combineFunction(add, 0)(square)(1, 3)