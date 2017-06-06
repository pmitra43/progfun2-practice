val xs = Stream.cons(1, Stream.cons(2, Stream.empty))

(1 to 1000).toStream

def streamRange(lo: Int, hi: Int): Stream[Int] = {
  if (lo >= hi) Stream.empty
  else lo #:: streamRange(lo + 1, hi)
}

streamRange(1, 10).take(3).toList

def isPrime(n: Int): Boolean = (2 until n) forall (n % _ != 0)

(streamRange(1000, 10000) filter isPrime) apply 1


def expr: Unit = {
  val x = {
    println("x"); 1
  }
  lazy val y = {
    println("y"); 2
  }

  def z = {
    println("z"); 3
  }

  z + y + x + z + y + x
}

expr

def from(n: Int): Stream[Int] = n #:: from(n + 1)

val nats = from(0)
val m4s = nats map (_ * 4)

(m4s take 10).toList

def sieve(s: Stream[Int]): Stream[Int] =
  s.head #:: sieve(s.tail filter (_ % s.head != 0))

val primes = sieve(from(2))

(primes take 100).toList

def sqrtStream(x: Double): Stream[Double] = {
  def improve(guess: Double) = {
    println("guess: " + guess)
    (guess + x / guess) / 2
  }
  lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
  guesses
}

def isGoodEnough(guess: Double, x: Double) =
  math.abs((guess * guess - x)/x) < 0.0001

sqrtStream(4).take(10).toList

sqrtStream(4).filter(isGoodEnough(_, 4)).take(10).toList