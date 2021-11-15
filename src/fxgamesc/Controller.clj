(ns fxgamesc.Controller
  (:require [fxgamesc.transitions :refer [get-node add-node add-raw]]))

(gen-class
  :name fxgamesc.Controller
  :prefix ct-
  :main false
  :init init
  :constructors {[] []})

(defn central []
  (.getCenter (get-node :frame)))

(defn ct-init[]
  (add-node :tiles-panel "tilespanel.fxml")
  (add-node :ttt-grid "tttgrid.fxml"))

