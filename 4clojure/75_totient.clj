(ns totient)

; Two numbers are coprime if their greatest common divisor equals 1. Euler's
; totient function f(x) is defined as the number of positive integers less than
; x which are coprime to x. The special case f(1) equals 1. Write a function
; which calculates Euler's totient function.


; This naive version works but is ugly and inefficient.
(def naive
  (fn [x]
    (let [gcd (fn gcd [a b]
          (if (= b 0) a (gcd b (mod a b))))]
      (if (= x 1) 1
          (loop [i 1 c 0]
            (if (= i x) c
                (if (= (gcd i x) 1)
                  (recur (+ i 1) (+ c 1))
                  (recur (+ i 1) c))))))))

(defn echoseq [s]
  (lazy-seq (cons (first s) (echoseq (rest s)))))

; A better solution would use the fact that if a number i < n is not coprime
; with n, then any of its multiples are also not coprime. So using something
; like a sieve algorithm and filtering those items out would be more efficient.

; The sieve example (exercise 64) uses a similar approach, so I thought I would
; try that. It's nice that it takes a sequence and returns a sequence.
;
; In this case, though, it doesn't make sense to be lazy, since we're not
; talking about an infinite sequence though. I would probably keep the basic
; idea but make this non-lazy, using the first and last elements. This example
; sends the runtime into an infinite loop. Not good!
;
(defn cp [s n]
  (let [gcd (fn gcd [a b]
              (if (= b 0) a (gcd b (mod a b))))
        f #(not= 0 (mod % (first s)))]
    (lazy-seq
     (cons (first s)
           (if (> (gcd (first s) n) 1)
             (cp (filter f (rest s)) n)
             (cp (rest s) n))))))

(take 3 (cp (iterate inc 1) 10))

(defn fib
         ([]
           (fib 1 1))
         ([a b]
           (lazy-seq (cons a (fib b (+ a b))))))

(take 7 (echoseq (iterate inc 1)))

(naive 1)

(naive 10)

(naive 40)

(naive 99)

; (= (__ 10) (count '(1 3 7 9)) 4)
