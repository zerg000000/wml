(ns wml.core
  (:use compojure.core
    ring.util.response
    ring.adapter.jetty)
  (:require [compojure.handler :as handler]
            [compojure.response :as response]
	    [compojure.route :as route]
	    [ring.middleware.json-params :as json-params]))

(defroutes main-routes
  (GET "/" [:as req] "<h1>Hello World</h1>" (.toString req))
  (route/not-found "<h1>Page not found</h1>"))

(def app 
  (-> (handler/site main-routes)
      json-params/wrap-json-params))


(defn -main [& args]
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty app {:port port})))
