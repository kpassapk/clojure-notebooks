(ns gcd)

; Euclid's theorem

(defn rec [a b]
  (if (= b 0) a (rec b (mod a b))))
