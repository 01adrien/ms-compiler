(define append
    (lambda (lst1 lst2)
        (if (null? lst1)
            lst2
            (cons (car lst1) 
                (append (cdr lst1) lst2)))))


(define length
    (lambda (lst)
        (if (null? lst)
            0
            (+ 1 (length (cdr lst))))))


(define map
  (lambda (f lst)
    (if (null? lst)
        nil
        (cons (f (car lst))
              (map f (cdr lst))))))


(define filter
  (lambda (pred lst)
    (if (null? lst)
        nil
        (if (pred (car lst))
            (cons (car lst)
                  (filter pred (cdr lst)))
            (filter pred (cdr lst))))))

(define reduce
  (lambda (f init lst)
    (if (null? lst)
        init
        (reduce f (f init (car lst)) (cdr lst)))))


(define max
  (lambda (a b)
    (if (> a b) a b)))

(define min
  (lambda (a b)
    (if (< a b) a b)))


(define factorial
  (lambda (n)
    (if (= n 0)
        1
        (* n (factorial (- n 1))))))


(define fib
  (lambda (n)
    (if (< n 2)
        n
        (+ (fib (- n 1)) (fib (- n 2))))))


(define even?
  (lambda (n)
    (if (= (mod n 2) 0) true false)))


(define odd?
  (lambda (n)
    (not (even? n))))