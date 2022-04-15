(def is-palindrome
  (fn [word]
    (let [seq (seq word)
          rev (reverse seq)]
      (= seq rev))))

(is-palindrome "aba")
