trait Generator[+T] {
  self =>
  def generate: T

  def map[S](f: T => S): Generator[S] = new Generator[S] {
    def generate = f(self.generate)
  }

  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    def generate = f(self.generate).generate
  }
}

val integers = new Generator[Int] {
  val rand = new java.util.Random

  override def generate = rand.nextInt()
}

def single[T](x: T): Generator[T] = new Generator[T] {
  def generate = x
}

def choose(lo: Int, hi: Int): Generator[Int] =
  for(x <- integers) yield lo + math.abs(x % (hi - lo))

val booleans = integers map (_ > 0)

def pairs[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] = for {
  x <- t
  y <- u
} yield (x, y)

def oneOf[T](xs: T*): Generator[T] =
  for(idx <- choose(0, xs.length)) yield xs(idx)
single(5).generate

choose(1, 5).generate

oneOf("red", "green", "blue", "yellow").generate


integers.generate
booleans.generate

def lists: Generator[List[Int]] = for {
  isEmpty <- booleans
  list <- if (isEmpty) emptyLists else nonEmptyLists
} yield list

def emptyLists = single(Nil)

def nonEmptyLists = for {
  head <- integers
  tail <- lists
} yield head :: tail

lists.generate

def test[T](g: Generator[T], noOfTimes: Int = 100)(test: T => Boolean) {
  for(_ <- 0 until noOfTimes) {
    val value = g.generate
    assert(test(value), "Test failed for: " + value)
  }
  println("Test passed " + noOfTimes + " times")
}

test(pairs(lists, lists)) {case (xs, ys) => (xs ++ ys).length > xs.length}