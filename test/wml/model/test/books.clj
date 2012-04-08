(ns wml.model.test.books
  (:require [wml.persistence.test.dumb-data       :as d]
            [wml.persistence.schema               :as schema]
            [wml.persistence.books                :as p])
  (:use clojure.test
        decline.core
        wml.model.books))

(deftest validate-optional-spec
  (testing "not exist"
    (let [check (optional :title (validate-val :title seq {:title [:empty]}))]
      (is (= nil (check {}))
      (is (= {:title [:empty]} (check {:title ""})))
    ))
)) 

(deftest validate-seq-spec
  (testing "map sequence"
    (let [check (validate-seq 
                    (validate-val :name seq {:name [:empty]}))]
      (is (= nil (check [{:name "tom"}])))
      (is (= {:name [:empty]} (check [{}])))
      (is (= nil (check [{:name "tom"} {:name "peter"}])))
      (is (= {:name [:empty]} (check [{:name "may"} {:title "ze"}])))
    )
  )
)
  
(deftest validate-book-spec
  (testing "check newly create book"
    (let [check validate-book]
      (is (= {:title [:required]} (check {:title ""})))
      (is (= {:title [:required]} (check {:title nil})))
      (is (= {:type [:empty]} (check {:title "the book"
                        :type []})))
      
    )
  )
)

(deftest new-book-spec
  (schema/create-schema)
  (testing "create a new book"
    (is (new-book {:title "a book"
                      :collections [
                        {:title "1" :href "steve-job/books/1"}
                      ]}))
    (is (= {:title [:required]} (new-book {})))
  )
  (schema/delete-schema)
)
