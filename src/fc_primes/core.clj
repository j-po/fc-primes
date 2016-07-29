(ns fc-primes.core
  (:require [clojure.pprint :refer [print-table]])
  (:import java.lang.Math)
  (:gen-class))

(defn nth-prime-upper-bound
  [n]
  (if (> 6 n)
    13
    (int (* n
            (+ (Math/log n)
               (Math/log (Math/log n)))))))

(defn sieve
  "Generate `times` prime numbers in O(sqrt(n)log(n)) time."
  [times]
  (let [upper-bound (nth-prime-upper-bound times)]
    (loop [i 1
           [new-prime & nums] (range 2 (inc upper-bound))
           primes []]
      (let [primes (conj primes new-prime)]
        (if (= i times)
          primes
          (recur (inc i)
                 (if (or (> 6 times)
                         (<= new-prime (Math/sqrt upper-bound)))
                   (filter #(not= 0 (mod % new-prime)) nums)
                   nums)
                 primes))))))

(defn multiplication-table [x factors]
  (map (partial * x) factors))

(defn print-multiples [n]
  (let [primes (sieve n)
        ;; A 1 in the top left would be confusing
        ;; even though it's convenient later. A
        ;; silly face will do nicely, though :-P
        labels (concat [:-P] primes)]
    (print-table labels
                 (map #(zipmap labels (multiplication-table % (concat [1] primes)))
                      primes))))

(defn -main []
  (print-multiples 10))
