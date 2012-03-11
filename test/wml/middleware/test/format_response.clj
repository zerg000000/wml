(ns wml.middleware.test.format-response
  (:use clojure.test
        hiccup.page
        wml.middleware.format-response))

(deftest html-accept-spec
  (testing "chrome common html request"
    (is (html-accept? {:headers {"accept" "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"}} {}))
    (is (not (html-accept? {:headers {"accept" "text/css,*/*;q=0.1"}} {})))  
    (is (not (html-accept? {:headers {"accept" "*/*"}} {}))))
)

(deftest view-resolve-spec
  (binding [*views-ns* ['wml.middleware.test.views]] 
    (testing "resolve views ns functions"
      (is (= "<!DOCTYPE html>\n<html><head><title>hello</title></head><body><h1>hello</h1></body></html>" ((view-resolve 'home) {:title "hello"})))
      (is (not (view-resolve 'not-exist-view)))
))
  (binding [*views-ns* ['wml.middleware.test.views 'views.error]]
    (testing "resolve multi-ns functions"
      (is (= "<!DOCTYPE html>\n<html><head><title>hello</title></head><body><h1>hello</h1></body></html>" ((view-resolve 'home) {:title "hello"})))
      (is (not (view-resolve 'not-exist-view)))
    ))
  (binding [*views-ns* ['views]]
    (testing "resolve sub-ns functions"
      (is (= "<!DOCTYPE html>\n<html><head><title>hello</title><link href=\"/css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\"><link href=\"/css/site.css\" rel=\"stylesheet\" type=\"text/css\"><link href=\"/css/style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><div class=\"navbar navbar-fixed-top\"><div class=\"navbar-inner\"><div class=\"container\"><a class=\"brand\" href=\"#\">Project name</a></div></div></div><div class=\"container\"><h1>hello</h1></div></body></html>" ((view-resolve 'main/index) {:id "hello"})))
   ))
)

