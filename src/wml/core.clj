(ns wml.core
  (:use compojure.core
	[ring.middleware.format-params :only [wrap-restful-params]]
	[ring.middleware.format-response :only [wrap-restful-response]]
        ring.util.response
        ring.adapter.jetty
        [wml.middleware.format-response :only [wrap-html-response]]
        [wml.routes.main :as main])
  (:require [compojure.handler :as handler]
	    ))
(def app 
  (-> (handler/api main/main-routes)
      (wrap-restful-params)
    ; (wrap-restful-response)
      (wrap-html-response)))

(defn -main [& args]
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty app {:port port})))
