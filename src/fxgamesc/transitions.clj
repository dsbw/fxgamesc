(ns fxgamesc.transitions
  (:require [clojure.java.io :as io]
            [fxgamesc.platform :refer [on-fx-thread]])
  (:import (javafx.fxml FXMLLoader)
           (javafx.animation Interpolator KeyValue KeyFrame Timeline)
           (javafx.util Duration)
           (javafx.event EventHandler)))

(def nodes (atom nil))

(defn add-raw [handle node]
  (swap! nodes assoc handle node))

(defn add-node [handle resource-name]
  (add-raw handle (FXMLLoader/load (io/resource resource-name)))
  nil)

(defn get-node [handle]
  (handle @nodes))

(defn switch-screen [scene new-root]
  (.setRoot scene (new-root @nodes)))

(defn activate
  ([new-node owner]
   (on-fx-thread
     (let [n (get-node new-node)
           _ (.set (.translateXProperty n) (.getWidth owner))
           _ (.remove (.getChildren owner) n)
           _ (.add (.getChildren owner) n)
           kv (KeyValue. (.translateXProperty n) 0 Interpolator/EASE_IN)
           kf (KeyFrame. (Duration/seconds 0.25) (into-array [kv]))
           tl (Timeline.)
           _ (.add (.getKeyFrames tl) kf)
           _ (.setOnFinished tl (proxy [EventHandler] [] (handle [e] (.setAll (.getChildren owner) (into-array [n])))))
           _ (.play tl)]
       (add-raw :active n))))
  ([new-node]
   (activate new-node (:central @nodes))))