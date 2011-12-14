(ns util.classpath
  (:require [clojure.java.io :as io])
  (:import (java.io File)
           (java.util.jar JarFile JarEntry)
           (java.net URL URLClassLoader)))

(defn- loader-classpath
  "Returns a sequence of File paths from a classloader."
  [loader]
  (when (instance? java.net.URLClassLoader loader)
    (map
     #(java.io.File. (.getPath ^java.net.URL %))
     (.getURLs ^java.net.URLClassLoader loader))))

(defn classpath
  "Returns a sequence of File objects of the elements on the classpath."
  ([classloader]
     (distinct
      (mapcat
       loader-classpath
       (take-while
        identity
        (iterate #(.getParent %) classloader)))))
  ([] (classpath (clojure.lang.RT/baseLoader))))

(defn current-thread-classpath []
  (classpath (.getContextClassLoader (Thread/currentThread))))

(defn- jar-file?
  "Returns true if file is a normal file with a .jar or .JAR extension."
  [f]
  (let [file (io/file f)]
    (and (.isFile file)
         (or (.endsWith (.getName file) ".jar")
             (.endsWith (.getName file) ".JAR")))))

(defn classpath-directories
  "Returns a sequence of File objects for the directories on classpath."
  []
  (filter #(.isDirectory ^File %) (current-thread-classpath)))

(defn classpath-jarfiles
  "Returns a sequence of JarFile objects for the JAR files on classpath."
  []
  (map #(JarFile. ^File %) (filter jar-file? (current-thread-classpath))))

