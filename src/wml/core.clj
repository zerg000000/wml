(ns wml.core
  (:use compojure.core
	[ring.middleware.format-params :only [wrap-restful-params]]
        ring.util.response
        ring.adapter.jetty
        [wml.middleware.format-response :only [wrap-html-response]]
        [wml.routes.main :as main])
  (:require [compojure.handler :as handler]
	    [ring.middleware.format-response :as resp]
	    ))
(def app 
  (-> (handler/api main/main-routes)
      (wrap-restful-params)
      (resp/wrap-json-response :predicate resp/json-accepted?)
      (wrap-html-response)
      (resp/wrap-yaml-in-html-response)
      ))

(defn -main [& args]
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty app {:port port})))
