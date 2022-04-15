(ns example)

[1 2 3 4]

[1 "two" 3 "four"]

[true 3 "four" 5]

[1 [true 3 "four" 5] 6]

(vector true 3 "four" 5)

(vector)

(def novels ["Emma" "Coma" "War and Peace"])

(count novels)

(first novels)

(rest novels)

(rest (rest novels))

(rest ["thid year"])

(def year-books ["1941" "April 1865" "1984" "2001"])

(nth year-books 3)

(year-books 1)

(conj novels "Carrie")

(cons "Carrye" novels)

'(1 (2 3) (3 4 2))

'(1 2 3 "four" 5 "six")

(def more-novels (conj novels "Jaws"))
