(defproject project "1.0.0-SNAPSHOT"
  :description "a secure book editing application"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [ring "1.0.2"]
		 [ring-json-params "0.1.3"]
		 [compojure "1.0.1"]]
  :dev-dependencies [[lein-ring "0.5.4"]]
  :ring {:handler wml.core/app}
  :main wml.core)
