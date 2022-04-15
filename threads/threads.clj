(ns threads)

(defn do-something-in-a-thread []
  (println "Hello from the thread.")
  (println "Goodbye from the thread."))

(def the-thread (Thread. do-something-in-a-thread))

(.start the-thread)

(defn do-something-else []
  (println "Hello from the thread.")
  (Thread/sleep 3000)
  (println "Good bye from the thread."))

(.start (Thread. do-something-else))

(def fav-book "Jaws")

(defn make-emma-favorite [] (def fav-book "Emma"))
(defn make-2001-favorite [] (def fav-book "2001"))

(make-emma-favorite)
(make-2001-favorite)

(.start (Thread. make-2001-favorite))
(.start (Thread. make-emma-favorite))

fav-book

(def inventory [{:title "Emma" :sold 51 :revenue 255}
                {:title "2001" :sold 17 :revenue 170}])


(def ^:dynamic *favorite-book* "Oliver Twist")

(def thread-1
  (Thread.
   #(binding [*favorite-book* "2001"]
      (println "My favorite book is" *favorite-book*))))

(def thread-2
  (Thread.
   #(binding [*favorite-book* "Emma"]
      (println "My favorite book is" *favorite-book*))))

(.start thread-1)
(.start thread-2)

(def del-thread (Thread. #(.delete (java.io.File "temp-titles.txt"))))

(.start del-thread)
(.join del-thread)

(def the-result (promise))

(deliver the-result "Emma")

(println "The value of my promise is " (deref the-result))
(println "The value of my promise is " @the-result)

(defn sum-copies-sold [inv]
  (apply + (map :sold inv)))

(defn sum-revenue [inv]
  (apply + (map :revenue inv)))

(let [copies-promise (promise)
      revenue-promise (promise)]
  (.start (Thread. #(deliver copies-promise (sum-copies-sold inventory))))
  (.start (Thread. #(deliver revenue-promise (sum-revenue inventory))))
  (println "The total number of books sold is " @copies-promise)
  (println "The total revenue is " @revenue-promise))

(def revenue-future
  (future (apply + (map :revenue inventory))))

(println "The total revenue is " @revenue-future)

(import java.util.concurrent.Executors)

;; Create a pool of at most three threads.

(def fixed-pool (Executors/newFixedThreadPool 3))

(defn a-lot-of-work []
  (println "Simulating fnction that takes a long time.")
  (Thread/sleep 1000)
  (println "Done"))

(defn even-more-work []
  (println "Simulating fnction that takes a long time.")
  (Thread/sleep 1000)
  (println "Done"))

(.execute fixed-pool a-lot-of-work)
