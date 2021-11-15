(ns fxgamesc.ttt.TttController)

(gen-class
  :name fxgamesc.ttt.TttController
  :prefix ttt-
  :main true
  :init init
  :constructors {[] []})

(defn ttt-init[]
  (println "Tic-tac-toe!"))

(defn ttt-nameChange[this e])