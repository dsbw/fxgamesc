(ns fxgamesc.TileController
  (:require [fxgamesc.transitions :refer [activate]]))

(gen-class
  :name fxgamesc.TileController
  :prefix tc-
  :main true
  :init init
  :constructors {[] []}
  :methods [[openTTT [javafx.scene.input.MouseEvent] void]])

(defn tc-init[]
  (println "TILES!"))

(defn tc-openTTT[this e]
  (println "Opening Tic-Tac-Toe!")
  (activate :ttt-grid))