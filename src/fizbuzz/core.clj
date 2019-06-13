(ns fizbuzz.core)

(def fizbuzz-min 1)
(def fizbuzz-max 100)

(def fiz "fiz")
(def buzz "buzz")
(def fizbuzz (str fiz buzz))

(defprotocol Kid
  (utterValue [this]))

(defrecord Fiz []
    Kid
  (utterValue [_] fiz))

(defrecord Buzz []
    Kid
  (utterValue [_] buzz))

(defrecord FizBuzz []
    Kid
    (utterValue [_] fizbuzz))

(defrecord PlainNumber [v]
  Kid
  (utterValue [_] v))

(defn convert-fizbuzz [n]
  (utterValue
   (cond
     (zero? (mod n 15)) (->FizBuzz)
     (zero? (mod n 3)) (->Fiz)
     (zero? (mod n 5)) (->Buzz)
     :default (->PlainNumber n))))

(defn fizbuzz-sequence []
  (map convert-fizbuzz (range fizbuzz-min
                              (inc fizbuzz-max))))

(defn print-sequence [seq]
  (dorun (map println seq)))

(defn fiz-buzz []
  (print-sequence (fizbuzz-sequence)))
