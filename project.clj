(defproject foo-db "0.1.0-SNAPSHOT"
            :description "FIXME: write this!"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [org.clojure/java.jdbc "0.1.1"]
                           [commons-dbcp/commons-dbcp "1.4"]
                           [noir "1.2.0"]]
            :dev-resources-path "ojdbc6.jar"
            :main foo-db.server)

