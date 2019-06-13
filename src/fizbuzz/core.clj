(ns fizbuzz.core)

(def fizbuzz-min 1)
(def fizbuzz-max 100)

(def fiz "fiz")
(def buzz "buzz")
(def fizbuzz (str fiz buzz))

(defprotocol Kid
  (utterValue [this]))

(defn- is-multiple? [n m]
  (zero? (mod n m)))

(defrecord PlainNumber [v]
  Kid
  (utterValue [_] v))

(defrecord Buzz [v]
  Kid
  (utterValue [_]
    (if (is-multiple? v 5)
      buzz
      (utterValue (->PlainNumber v)))))

(defrecord Fiz [v]
  Kid
  (utterValue [_]
    (if (is-multiple? v 3)
      fiz
      (utterValue (->Buzz v)))))

(defrecord FizBuzz [v]
  Kid
  (utterValue [_]
    (if (is-multiple? v 15)
      fizbuzz
      (utterValue (->Fiz v)))))

(defn convert-fizbuzz [n]
  (utterValue (->FizBuzz n)))

(defn fizbuzz-sequence []
  (map convert-fizbuzz (range fizbuzz-min
                              (inc fizbuzz-max))))

(defn print-sequence [seq]
  (dorun (map println seq)))

(defn fiz-buzz []
  (print-sequence (fizbuzz-sequence)))
