(ns util.server
  (:require [util.namespace :as namespace]))

(defn load-views-ns
  "Require all the namespaces prefixed by the namespace symbol given so that the pages
  are loaded by the server."
  [& ns-syms]
  (doseq [ns-sym ns-syms
          n (namespace/find-namespaces-on-classpath)
          :let [pattern (re-pattern (name ns-sym))]
          :when (re-seq pattern (name n))]
    (require n)))
