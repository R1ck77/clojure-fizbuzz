(ns fizbuzz.core)

(def fizbuzz-min 1)
(def fizbuzz-max 100)

(def fiz "fiz")
(def buzz "buzz")
(def fizbuzz (str fiz buzz))

(defn convert-fizbuzz [n]
  (cond
    (= 0 (mod n 15)) fizbuzz
    (= 0 (mod n 3)) fiz
    (= 0 (mod n 5)) buzz
    :default n))

(defn fizbuzz-sequence []
  (map convert-fizbuzz (range fizbuzz-min
                              (inc fizbuzz-max))))

(defn print-sequence [seq]
  (dorun (map println seq)))

(defn fiz-buzz []
  (print-sequence (fizbuzz-sequence)))
