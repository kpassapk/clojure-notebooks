(ns triangle
  (:require [clojure.math.combinatorics :as combo]))

; Write a function which calculates the sum of the minimal path through a
; triangle. The triangle is represented as a vector of vectors. The path should
; start at the top of the triangle and move to an adjacent number on the next
; row until the bottom of the triangle is reached.

; the paths are n, n+1 in the next

(def traversals (combo/selections [0 1] 3))

; given a traversal, get a sum


(def tree [   [1]
          [2 4]
         [5 1 4]
           [2 3 4 5]])

(map [0 0 0] tree)

((tree 1) 0)

(tree 1)

(subvec tree 1 1)
