(ns wml.model.test.core
  (:use clojure.test
        decline.core
        wml.model.core))

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
