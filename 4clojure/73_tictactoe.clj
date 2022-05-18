(ns tictactoe)

; A tic-tac-toe board is represented by a two dimensional vector. X is
; represented by :x, O is represented by :o, and empty is represented by :e. A
; player wins by placing three Xs or three Os in a horizontal, vertical, or
; diagonal row. Write a function which analyzes a tic-tac-toe board and
; returns :x if X has won, :o if O has won, and nil if neither player has won.

(def detect
  (fn [board]
    (let [unrolled (vec (apply concat board))
          winners '((0 1 2) (3 4 5) (6 7 8)
                    (0 3 6) (1 4 7) (2 5 8)
                    (0 4 8) (2 4 6))]
      (->> winners
           (map #(map unrolled %))
           (map #(into #{} %))
           (filter #(= (count %) 1))
           (map #(remove #{:e} %))
           flatten first))))

(ns tictctoe-test
  (:require [clojure.test :as t]
            [tictactoe :as tic]))


(map (vec (apply concat [[:e :e :e]
                         [:e :e :e]
                         [:e :e :e]])) [1 2])

(def board
  [[:e :e :e]
   [:e :e :e]
   [:e :e :e]])

(def board2
  [[:x :e :o]
   [:x :o :e]
   [:o :e :x]])

(tic/detect board)
(tic/detect board2)
