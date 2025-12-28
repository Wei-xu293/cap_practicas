(ns cavall)

; BL square (1, 1)

(def row 8)
(def column 8)

(defn dins [pos]
  (let [sz (count pos) [n m] pos]
    (assert (= 2 sz))
    (cond 
      (or (< n 1) (> n row) (< m 1) (> m column)) 
      false
      
      :else true)))

; (i j)
(def salts ['(-2 -1) '(-1 -2) '(1 -2) '(2 -1) 
            '(2 1) '(1 2) '(-1 2) '(-2 1)])

(defn jumpTo [i s]
  (let [[a b] i [x y] s]
    (list (+ a x) (+ b y))))

(defn moviments [pos]
  (let [sz (count pos)]
    (assert (= 2 sz))
    (filter dins (map (partial jumpTo pos) salts))))


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