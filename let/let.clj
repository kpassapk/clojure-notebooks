(ns let)

(defn compute-discount-amount [amount discount-percent min-charge]
  (if (> (* amount (- 1.0 discount-percent)) min-charge)
    (* amount (- 1.0 discount-percent))
    min-charge))

(defn compute-discount-amount [amount discount-percent min-charge]
  (let [discounted-amount (* amount (- 1.0 discount-percent))]
    (if (> discounted-amount min-charge)
      discounted-amount
      min-charge)))

(defn compute-discount-amount [amount discount-percent min-charge]
  (let [discount (* amount discount-percent)
        discounted-amount (- amount discount)]
    (if (> discounted-amount min-charge)
      discounted-amount
      min-charge)
    ))

(def user-discounts
  {"Nicholas" 0.10 "Jonathan" 0.07 "Felicia" 1})

(defn mk-discount-price-f [user-name user-discounts min-charge]
  (let [discount-percent (user-discounts user-name)]
    (fn [amount]
      (let [discount (* amount discount-percent)
            discounted-amount (- amount discount)]
        (if (> discounted-amount min-charge)
          discounted-amount
          min-charge)
        ))))

(def compute-felicia-price (mk-discount-price-f "Felicia" user-discounts 10.0))

(compute-felicia-price 20.0)

(defn uppercase-author [book]
  (if-let [author (:author book)]
      (.toUpperCase author)))

(defn uppercase-author [book]
  (if-let [author (:author book)]
    (.toUpperCase author)
    "Anonymouse"))
