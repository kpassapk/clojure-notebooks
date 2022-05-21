(ns gcd)

; Euclid's theorem. Given two numbers, find the greatest common divisor.

(defn rec [a b]
  (if (= b 0) a (rec b (mod a b))))

