(ns fxgamesc.core
  (:require [clojure.java.io :as io]
            [fxgamesc.platform :refer [on-fx-thread]]
            [fxgamesc.Controller :refer [central]]
            [fxgamesc.transitions :refer [add-node switch-screen get-node activate add-raw]])
  (:import (javafx.stage Stage)
           (javafx.scene Scene Group)))

(def s (on-fx-thread (Stage.)))
(.setTitle @s "Fun 'n' Games with JavaFX -- Clojure Style")
(def t (Scene. (Group.) 800 600))
(on-fx-thread (.setScene @s t))
(on-fx-thread (.show @s))
(add-node :frame "frame.fxml")
(add-raw :central (central))
(switch-screen t :frame)
(.add (.getStylesheets t)  (.toExternalForm (io/resource "styles.css")))
(activate :tiles-panel)