(ns groupby.core
  (:require [clojure.string :as string])
  (:gen-class))

(defn read-file [path]
  (slurp path))

(defn split-into-vals [line]
  (string/split line #"\s"))


(defn get-total [group]
  (let [what-to-sum (filter #(not (nil? %)) (map #(second %) group))]
    (reduce + what-to-sum)
  ))

(defn get-group-total [group]
  (hash-map (first group) (get-total group)))

(defn -main
  [& args]
  (let [path "/home/ccrowe/Documents/github/groupby/src/groupby/num-ad-page-sets-by-insight-tag-account.txt"
        text (read-file path)
        lines (string/split text #"\n")
        lines-values (map split-into-vals lines)
        groups (group-by #(first %) lines-values)
        group-totals (map get-group-total groups)]
    (println group-totals)
    ))

