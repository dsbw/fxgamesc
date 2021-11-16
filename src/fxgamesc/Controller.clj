(ns fxgamesc.Controller
  (:require [fxgamesc.transitions :refer [get-node add-node activate]]))

(gen-class
  :name fxgamesc.Controller
  :init init
  :constructors {[] []}
  :methods [[navigateBackwards [javafx.event.ActionEvent] void]])

(defn central []
  (.getCenter (get-node :frame)))

(defn -init []
  (add-node :tiles-panel "tilespanel.fxml")
  (add-node :ttt-grid "tttgrid.fxml"))

(defn -navigateBackwards [this e]
  (if (not= (get-node :active) (get-node :tiles-panel))
         (activate :tiles-panel)))