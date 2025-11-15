(ns knn 
  (:require
    [clojure.math :as m]))


(defn foldr [f x0 s]
  (if (empty? s) x0
      (let [[cap & cua] s]
        (f cap (foldr f x0 cua)))))

(def fold foldr)

(defn de [v w] 
  (let [u (map vector v w)]
    (m/sqrt (fold #(+ (* (- (first %1) (second %1)) (- (first %1) (second %1))) %2) 0 u))))

(defn dm [v w]
  (let [u (map vector v w)]
    (reduce #(+ (abs (- (first %2) (second %2))) %1) 0 u)))

(defn dh [v w]
  (let [n (count v) m (count w) u (map vector v w)]
    (assert (= n m))
    (/ (reduce #(if (= (first %2) (second %2)) %1 (inc %1)) 0 u) n)))

;(use 'knn :reload-all)


(println (de [1 0] [1 1]))
(println (de [0 0 0] [1 1 1]))
(println (dm [1 0] [1 1]))
(println (dm [0 0 0] [1 1 1]))
(println (dh [:a :b] [:b :b]))
(println (dh [:a :a :a] [:b :b :b]))