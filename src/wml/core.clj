(ns wml.core
  (:use compojure.core
	[ring.middleware.format-params :only [wrap-restful-params]]
        ring.util.response
        ring.adapter.jetty
        [wml.routes.main :as main])
  (:require [compojure.handler :as handler]
	    [ring.middleware.format-response :as resp]
            [clojurewerkz.elastisch.rest :as rest]
	    ))

(reset! rest/*endpoint* (rest/ElasticSearchEndpoint. 
  (get (System/getenv) "BONSAI_INDEX_URL" "http://localhost:9200/")))

(def app 
  (-> (handler/api main/main-routes)
      (wrap-restful-params)
      (resp/wrap-restful-response)  
      ))

(defn -main [& args]
    (run-jetty app {:port (Integer. (nth args 2))}))
