(ns wml.core
  (:use compojure.core
	[ring.middleware.format-params :only [wrap-restful-params]]
	[ring.middleware.format-response :only [wrap-restful-response]]
        ring.util.response
        ring.adapter.jetty)
  (:require [compojure.handler :as handler]
            [compojure.response :as response]
	    [compojure.route :as route]
	    ))

(defroutes main-routes
  (GET "/" [:as req] "<h1>Hello World</h1>" (.toString req))
  (route/not-found "<h1>Page not found</h1>"))

(def app 
  (-> (handler/site main-routes)
      (wrap-restful-params)
      (wrap-restful-response)))


(defn -main [& args]
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty app {:port port})))
