(ns wml.core
  (:use compojure.core
	[ring.middleware.format-params :only [wrap-restful-params]]
        ring.util.response
        ring.adapter.jetty
        [wml.routes.main :as main])
  (:require [compojure.handler :as handler]
	    [ring.middleware.format-response :as resp]
	    ))
(def app 
  (-> (handler/api main/main-routes)
      (wrap-restful-params)
      (resp/wrap-restful-response)  
      ))

(defn -main [& args]
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty app {:port port})))
