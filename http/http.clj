(ns http
  (:require [org.httpkit.client :as client]))

(def foo @(client/get "https://practicalli.github.io/blog/"))
