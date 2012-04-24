(defproject project "1.0.0-SNAPSHOT"
  :description "a simple book editing application"
  :repositories { "clojure-releases" "http://build.clojure.org/releases",
                  "sonatype" { :url "http://oss.sonatype.org/content/repositories/releases" 
                               :snapshots false,
                               :releases {:checksum :fail, :update :always}}
  }
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [hiccup "1.0.0"]
                 [hiccup-bootstrap "0.1.0"]
                 [ring "1.1.0"]
		 [compojure "1.0.2"]
		 [ring-middleware-format "0.2.0-SNAPSHOT"]
                 [clj-decline "0.0.5"]
                 [pretzel "0.2.3"]
                 [clojurewerkz/elastisch "1.0.0-SNAPSHOT"]
                 ]
  :plugins [[lein-ring "0.6.4"]
            [lein-swank "1.4.2"]]
  :ring {:handler wml.core/app}
  :main wml.core)
