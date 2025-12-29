(ns cavall)

; BL square (1, 1)

(def row 8)
(def column 8)

(defn dins [pos]
  (let [sz (count pos) [x y] pos]
    (assert (= 2 sz))
    (and (<= 1 x row)
         (<= 1 y column))))

; (i j)
(def salts '((-2 -1) (-1 -2) (1 -2) (2 -1) 
             (2 1) (1 2) (-1 2) (-2 1)))

(defn jumpTo [i s]
  (let [[a b] i [x y] s]
    (list (+ a x) (+ b y))))

(defn moviments [pos]
  (let [sz (count pos)]
    (assert (= 2 sz))
    (filter dins (map (partial jumpTo pos) salts))))

; (mapcat moviments (moviments '(1 1)))

(defn some-reducing [pred]
  (fn [rf]
    (fn 
      ([] (rf))
      ([result] (rf result))
      ([result val]
       (if (pred val)
         (reduced true)
         result)))))

(defn pot-anar3 [ini des]
  (let [sz1 (count ini) sz2 (count des)]
    (assert (and (= sz1 2) (= sz2 2)))
    (transduce (comp 
                (mapcat moviments)
                (mapcat moviments)
                (mapcat moviments)
                ;(some-reducing #(= des %))
                (filter #(= des %))
                (take 1))
               (fn
                 ([r] r)
                 ([_ _] true))
               false
               (list ini))))

; (use 'cavall :reload-all)
; Jocs de Proves
; (dins '(4 5)) 👉 true
; (dins '(0 1)) 👉 false
; (dins '(4 9)) 👉 false
; (moviments '(4 5)) 👉 ((2 4) (2 6) (3 3) (3 7)
;                        (5 3) (5 7) (6 4) (6 6))
; (moviments '(1 1)) 👉 ((2 3) (3 2))
; (pot-anar3 '(1 1) '(4 5)) 👉 true
; (pot-anar3 '(1 1) '(4 6)) 👉 false


; ref: https://clojure.org/reference/transducers