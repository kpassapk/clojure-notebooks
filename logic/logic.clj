(ns logic)

(defn print-greetig [preferred-customer order-amount]
  (if preferred-customer
    0.0
    (* order-amount 0.10)))

(print-greetig false 10)

(= (+ 2 2) (- 5 1) (- 10 6))

(not= "Ana" "Jane")

(number? 1984)

(map? {:title 1984})

(if "hello" "Yes" "No")

(if 0 "yes" "no")

(do
  (println "One")
  (println 3))

(defn shipping-charge[preferred-customer order-amount]
  (if preferred-customer
    (do
      (println "Preferred customer, free shipping!")
      0.0)
    (do
      (println "Regular customer, charge them for shipping")
      (* order-amount 0.10))
    ))

(shipping-charge false 10)

(when true
  (println "Hello returning customer!")
  (println "Welcome back to Blotts Books"))


(defn shipping-charge [preferred-customer order-amount]
  (cond
    preferred-customer 0.0
    (< order-amount 50.0) 5.0
    (< order-amount 100.0) 10.0
    :else (* 0.1 order-amount)))

(defn customer-greetig [status]
  (case status
    :gold "Welcome, welcome, welcome back!!"
    :preferred "Welcome back!"
    "Welcome to Blotts Books"))

(shipping-charge false 150)

(customer-greetig :gold)

(try
  (publish-book book)
  (catch ArithmeticException e (println "Math problem."))
  (catch StackOverflowError e (println "Unable to publish..")))

(defn publish-book [book]
  (when (not (:title book))
    (throw (ex-info "A book needs a title!", {:book book}))))
