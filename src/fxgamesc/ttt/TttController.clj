(ns fxgamesc.ttt.TttController
  (:require [fxgamesc.transitions :as transitions])
  (:import (javafx.scene.input TransferMode ClipboardContent)
           (javafx.scene.image ImageView)
           (javafx.scene.layout GridPane)))

(gen-class
  :name fxgamesc.ttt.TttController
  :init init
  :constructors {[] []}
  :state board
  :methods [[onDragDetected [javafx.scene.input.MouseEvent] void]
            [onDragOver [javafx.scene.input.DragEvent] void]
            [onDragDrop [javafx.scene.input.DragEvent] void]])

(defn get-coord [dim coord numberOfSections]
  (let [section-size (Math/round (float (/ dim numberOfSections)))
        section-number (int (quot (dec coord) section-size))]
    (Integer/valueOf (min section-number (dec numberOfSections)))))

(defn set-cell [this x y v]
  (swap! (.board this) assoc [x y] v))

(defn get-cell [this x y]
  (get (.board this) [x y]))

(defn git [x]
  (.lookup (transitions/get-node :ttt-grid) (str "#" x)))

(defn mouse-to-coord [e]
  (let [board (git "board")
        col (get-coord (.getWidth board) (.getX e) 3)
        row (get-coord (.getHeight board) (.getY e) 3)]
    [col row]))

(defn -init []
  (println "Tic-tac-toe!")
  [[] (atom {})])

(defn -onDragDetected [this e]
  (let [db (.startDragAndDrop (.getTarget e) TransferMode/ANY)
        content (ClipboardContent.)
        _ (.putString content (.getId (.getSource e)))
        _ (.setContent db content)
        ]))

(defn -onDragOver [this e]
  (let [[col row] (mouse-to-coord e)]
    (if-not (get-cell this col row)
      (if (and (not= (.getGestureSource e) (git "board"))
               (.hasString (.getDragboard e)))
        (.acceptTransferModes e TransferMode/COPY_OR_MOVE))))
  (.consume e))

(defn -onDragDrop [this e]
  (let [[col row] (mouse-to-coord e)
        pc (.getString (.getDragboard e))
        _ (set-cell this col row pc)
        xo (ImageView. (.getImage (git pc)))
        board (git "board")]
    (.setFitWidth xo (/ (.getWidth board) 3))
    (.setFitHeight xo (/ (.getHeight board) 3))
    (GridPane/setRowIndex xo row)
    (GridPane/setColumnIndex xo col)
    (.addAll (.getChildren board) [xo])))
