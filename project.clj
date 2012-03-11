(defproject project "1.0.0-SNAPSHOT"
  :description "a secure book editing application"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [hiccup "1.0.0-beta1"]
                 [ring "1.0.2"]
		 [compojure "1.0.1"]
		 [org.clojars.mikejs/ring-etag-middleware "0.1.0-SNAPSHOT"]
		 [ring-middleware-format "0.1.2-SNAPSHOT"]]
  :dev-dependencies [[lein-ring "0.5.4"]]
  :ring {:handler wml.core/app}
  :main wml.core)
