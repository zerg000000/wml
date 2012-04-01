(ns wml.persistence.test.books
  (:require [wml.persistence.test.dumb-data       :as d]
            [wml.persistence.schema               :as schema]
            [wml.persistence.books                :as books])
  (:use clojure.test))

(deftest save-spec
  (def sec-id (atom 1))
  (def b-id (atom 1))
  (schema/create-schema)
  (let [
     sec-id-1  (books/save-section d/section-ch1 (swap! sec-id + 1))
     sec-id-2  (books/save-section d/section-ch2 (swap! sec-id + 1))
     sec-id-3  (books/save-section d/section-ch3 (swap! sec-id + 1))
     book-id   (books/save-book d/book-hamlet (swap! b-id + 1))]
    (testing "save book into datastore"
      (is (= d/book-hamlet (:_source (books/get-book (:_id book-id)))))
      (is (= d/section-ch1 (:_source (books/get-section (:_id sec-id-1)))))
    ))
  (schema/delete-schema))