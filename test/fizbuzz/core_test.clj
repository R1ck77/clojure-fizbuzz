(ns fizbuzz.core-test
  (:require [clojure.test :refer :all]
            [fizbuzz.core :refer :all]))

(def expected-list-size 100)

(deftest test-convert-fizbuzz
  (testing "returns the number for non 3 non 5 multiples"
    (is (= 1 (convert-fizbuzz 1)))
    (is (= 2 (convert-fizbuzz 2)))
    (is (= 4 (convert-fizbuzz 4)))
    (is (= 7 (convert-fizbuzz 7))))
  (testing "returns fiz for multiples of 3 but not 5"
    (is (= fiz (convert-fizbuzz 3)))
    (is (= fiz (convert-fizbuzz 6)))
    (is (= fiz (convert-fizbuzz 9)))
    (is (= fiz (convert-fizbuzz 12))))
  (testing "returns buzz for multiples of 5 but not 3"
    (is (= buzz (convert-fizbuzz 5)))
    (is (= buzz (convert-fizbuzz 10)))
    (is (= buzz (convert-fizbuzz 20))))
  (testing "returns fizbuzz for multiples of both 3 and 5"
    (is (= fizbuzz (convert-fizbuzz 15)))
    (is (= fizbuzz (convert-fizbuzz 30)))
    (is (= fizbuzz (convert-fizbuzz 45)))))

(deftest test-fizbuzz-sequence
  (testing "returns 100 elements"
    (is (= expected-list-size (count (fizbuzz-sequence)))))
  (testing "gets the first 16 elements right"
    (is (= [1 2 fiz 4 buzz fiz 7 8 fiz buzz 11 fiz 13 14 fizbuzz 16]
           (take 16 (fizbuzz-sequence)))))
  (testing "gets the last 2 elements right"
    (is (= [buzz fiz]
           (take 2 (reverse (fizbuzz-sequence)))))))

(deftest test-print-sequence
  (testing "prints a sequence one element per line"
    (is (= "1\n2\nfoo\n"
           (with-out-str (print-sequence '(1 2 "foo")))))))

(deftest test-fiz-buzz
  (testing "prints the first 16 elements correctly"
    (let [expected (map str [1 2 fiz 4 buzz fiz 7 8 fiz buzz 11 fiz 13 14 fizbuzz 16])]
      (is (= expected
             (take (count expected) (clojure.string/split (with-out-str (fiz-buzz)) #"\n"))))))
  (testing "fiz-buzz uses print-sequence for printing"
    (let [print-sequence-last-argument (atom nil)]
      (with-redefs [print-sequence #(reset! print-sequence-last-argument %)]
        (is (= "" (with-out-str (fiz-buzz)))))
      (is (= expected-list-size (count @print-sequence-last-argument))))))
