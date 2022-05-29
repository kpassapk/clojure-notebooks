(ns tramp)

; Reimplement the function described in <a href="76"> "Intro to Trampoline"</a>.

(defn foo [x]
   (if (< x 0)
     (println "done")
     #(foo (do (println :x x) (dec x)))))


; If the f is a function, keep calling it with the results of the previous invocation.
; Otherwise just return the value.

(def stupid
  (fn [f & args]
    (let [r (apply f args)]
      (loop [f r]
        (if (fn? f)
          (recur (f))
          f)))))

(stupid foo 10)

(fn? foo)
; How do I discover if something is invocable / callable

(type 10)

(type #())

(def f "bla")

'f

;; (= (letfn [(triple [x] #(sub-two (* 3 x)))
;;           (sub-two [x] #(stop?(- x 2)))
;;           (stop? [x] (if (> x 50) x #(triple x)))]
;;     (__ triple 2))
;;    82)

;; (= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
;;           (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
;;     (map (partial __ my-even?) (range 6)))
;;   [true false true false true false])
