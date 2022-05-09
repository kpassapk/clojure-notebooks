(ns bbox)

; Clojure has many collection types, which act in subtly different ways. The
; core functions typically convert them into a uniform "sequence" type and work
; with them that way, but it can be important to understand the behavioral and
; performance differences so that you know which kind is appropriate for your
; application. Write a function which takes a collection and returns one of:
; map, :set, :list, or :vector - describing the type of collection it was given.
; You won't be allowed to inspect their class or use the built-in predicates
; like list? - the point is to poke at them and understand their behavior.
;
; Special Restrictions : class,type,Class,vector?,sequential?,list?,seq?,map?,set?,instance?,getClass


; First attempt - fail!
; - a hash is not a sequence
; - sequences of length 0 will fail
(defn guess-by-conj [coll]
  (let [test1 (try (conj coll ::a)
                   (catch IllegalArgumentException _ :map))]
    (if (seq? test1)
      (let [test2 (conj coll (first coll))]
        (cond
          (= (count test2) (count test1)) :set
          (= ::a (last test1)) :vector
          :else :list))
      test1)))

; Here is a macro returning (and not printing)
; the current time in milliseconds
(defmacro ms
  [expr]
  `(let [start# (. System (currentTimeMillis))
         ret# ~expr]
     (- (. System (currentTimeMillis)) start#)))

; Detect
(defn detect [el]
  (try
    (let [coll #(into %1 (range 0 1000))
          test #(dotimes [_ 1e7] (cons 1 %))
          ord [:list :vector :set]
          protos [() [] #{}]
          emp (empty el)
          time-ms (fn [expr & args]
                    (let [start (. System (currentTimeMillis))]
                      (apply expr args)
                      (- (. System (currentTimeMillis)) start)))
          res (time-ms test (coll emp))]
      (->> (for [c (map coll protos)]
             (time-ms test c))
           (map #(Math/abs (- res %)))
           (map-indexed vector)
           (apply min-key second)
           (first)
           (ord)))
    (catch Exception _ :map)))

(detect {})

; Try
(= :map (detect {:a 1, :b 2}))
(= :list (detect (range (rand-int 20))))
(= :vector (detect [1 2 3 4 5 6]))
(= :set (detect #{10 (rand-int 5)}))
(= [:map :set :vector :list] (map detect [{} #{} [] ()]))
