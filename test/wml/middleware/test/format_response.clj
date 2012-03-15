(ns wml.middleware.test.format-response
  (:use clojure.test
        hiccup.page
        wml.middleware.format-response))

(deftest wrap-template-engine-spec
  (testing "return empty string when cannot encode"
    (let [wrapper (wrap-template-engine (fn [a b] nil))]
      (is (= "" (wrapper "some"))))
  )
)

(deftest ns-view-resolve-spec
  (let [resolver (ns-view-resolve ['wml.middleware.test.views])] 
    (testing "resolve views ns functions"
      (is (= "<!DOCTYPE html>\n<html><head><title>hello</title></head><body><h1>hello</h1></body></html>" ((resolver 'home) {:title "hello"})))
      (is (not (resolver 'not-exist-view)))
))
  (let [resolver (ns-view-resolve ['wml.middleware.test.views 'views.error])]
    (testing "resolve multi-ns functions"
      (is (= "<!DOCTYPE html>\n<html><head><title>hello</title></head><body><h1>hello</h1></body></html>" ((resolver 'home) {:title "hello"})))
      (is (not (resolver 'not-exist-view)))
    ))
  (let [resolver (ns-view-resolve ['views])]
    (testing "resolve sub-ns functions"
      (is (= "<!DOCTYPE html>\n<html><head><title>hello</title><link href=\"/css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\"><link href=\"/css/site.css\" rel=\"stylesheet\" type=\"text/css\"><link href=\"/css/style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><div class=\"navbar navbar-fixed-top\"><div class=\"navbar-inner\"><div class=\"container\"><a class=\"brand\" href=\"#\">Project name</a></div></div></div><div class=\"container\"><h1>hello</h1></div></body></html>" ((resolver 'main/index) {:id "hello"})))
   ))
)

