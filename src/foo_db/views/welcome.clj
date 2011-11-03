(ns foo-db.views.welcome
  (:require [foo-db.views.common :as common]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.core
        util.db-utils
        clojure.java.jdbc
        hiccup.page-helpers))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to foo-db"]))


(def data-source (setup-data-source "jdbc:oracle:thin:mydba2/mydbadev890@172.16.7.39:1521/orclsh"))
(def db {:datasource data-source})

(defpage "/app" []
         (with-connection db (with-query-results res ["select * from compute_com_record"] (str res))))
