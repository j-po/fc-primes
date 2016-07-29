(ns fc-primes.core-test
  (:require [clojure.test :refer :all]
            [fc-primes.core :refer :all])
  (:import java.lang.Math))

(defn prime? 
  "The (second-)most naive possible primality test."
  [n]
  (case n
    2 true
    (every? #(not= 0 (rem n %))
            (range 2 (inc (int (Math/sqrt n)))))))

(deftest primes
  (testing "Generated primes are prime."
     (is (every? prime? (sieve 1000))))
  (testing "Generating the correct number of primes"
     (are [n] (= n (count (sieve n)))
          1
          10
          100
          1000)))
