(defun read-data (data)
  (mapcar 'string-to-number (split-string data "," t)))

(defun next-value (value)
  (let ((new-value (1- value)))
    (if (eq new-value -1) (list 6 8) (list new-value))))

(defun process-list (values counter)
  (if (eq counter 0) values
    (process-list (flatten-tree (mapcar 'next-value values)) (1- counter))))

(defun group-and-count(values my-hash)
  (setq current (car values))
  (setq item (gethash current my-hash))
  (if item (puthash current (1+ item) my-hash) (puthash current 1 my-hash))
  (if (cdr values) (group-and-count (cdr values) my-hash)
    ()))

(defun display-hash-table (hash)
  (let (keys)
    (maphash (lambda (key value)
               (setq keys (cons key keys)))
             hash)
    (mapcar (lambda (key)
              (concat (number-to-string key) " => " (number-to-string (gethash key hash))))
            (sort keys '<))))

(defun hash-table-get-keys (hash)
  (let (keys)
    (maphash (lambda (key value)
               (setq keys (cons key keys)))
             hash)
    (sort keys '<)))

(defun hash-table-increment-keys (hash)
  (let ((keys (hash-table-get-keys hash))
	(newhash (make-hash-table)))
    (mapc (lambda (key)
	      (hash-table-increment-key key hash newhash))
	    (sort keys '<))
    newhash))


(defun hash-table-increment-key (key hash newhash)
  (let ((item (gethash key hash))
	(newValue (1- key)))
    (if (eq newValue -1)
	(let ((oldValue (if (gethash 6 newhash) (gethash 6 newhash) 0)))
	  (puthash 6 (+ item oldValue) newhash)
	  (puthash 8 item newhash))
      (let ((oldValue (if (gethash newValue newhash) (gethash newValue newhash) 0)))
	(puthash newValue (+ item oldValue) newhash)))))


(defun process-values (values counter)
  (let ((my-hash (make-hash-table)))
    (group-and-count values my-hash)
    (process-next-values my-hash counter)))

(defun count-values (hash)
  (let ((sum 0))
    (maphash (lambda (key value)
               (setq sum (+ sum value)))
             hash)
    (concat "Sum: " (number-to-string sum))))

(defun process-next-values (my-hash counter)
  (let ((current-sum (count-values my-hash)))
    (if (eq counter 0)
	(with-temp-buffer
	  (insert (format "%s\n" (display-hash-table my-hash)))
	  (insert (format "%s" (count-values my-hash)))
	  (clipboard-kill-region (point-min) (point-max)))
      (process-next-values (hash-table-increment-keys my-hash) (1- counter)))))



