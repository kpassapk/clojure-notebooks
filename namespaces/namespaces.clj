(ns namespaces
  (:require [clojure.data :as d]))

(def literature ["Emma" "Oliver Twist" "Possession"])

(def horror ["It" "Carry" "Posession"])

(d/diff literature horror)

(ns pricing)

(def discount-rate 0.15)

discount-rate
(ns-unmap *ns* 'discount-rate)

(ns user)

(find-ns 'user)

(ns-map 'namespaces)

(namespace 'pricing/discount-print)

(def discount-rate )
