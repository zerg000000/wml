(ns wml.middleware.test.views
  (:use hiccup.page))

(defn home [data]
  (html5 [:head [:title (:title data)]] [:body [:h1 "hello"]]))
