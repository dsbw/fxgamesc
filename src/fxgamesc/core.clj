(ns fxgamesc.core
  (:require [clojure.java.io :as io]
            [fxgamesc.platform :refer [on-fx-thread initialize]]
            [fxgamesc.Controller])
  (:import (javafx.stage Stage)
           (javafx.scene Scene Group)
           (javafx.fxml FXMLLoader)))

(initialize)

(def screens (atom nil))
(defn add-screen [handle resource-name]
  (swap! screens assoc handle (FXMLLoader/load (io/resource resource-name))))
(defn switch-screen [scene new-root]
  (.setRoot scene (new-root @screens)))

(def s (on-fx-thread (Stage.)))
(.setTitle @s "Fun 'n' Games with JavaFX -- Clojure Style")
(def t (Scene. (Group.) 800 600))
(on-fx-thread (.setScene @s t))
(on-fx-thread (.show @s))
(add-screen :frame "frame.fxml")
(switch-screen t :frame)
(.add (.getStylesheets t)  (.toExternalForm (io/resource "styles.css")))
