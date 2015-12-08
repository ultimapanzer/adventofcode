(ns advent-clj.day3
  (:gen-class)
  (:require [clj-http.client :as client]))

;; Advent of code Day 3 - pt 1

(defn get-input [url cookie]
  (:body (client/get "http://adventofcode.com/day/3/input" {:cookies cookie})))

(defn get-next-pos [[x y :as pos] dir]
  (case dir
    ">" [(inc x) y]
    "<" [(dec x) y]
    "^" [x (dec y)]
    "v" [x (inc y)]
    pos))

(defn deliver-gifts [position directions houses]
  (let [[x y :as next-pos] (get-next-pos position (str (first directions)))]
    (if (empty? directions)
      (count (keys houses))
      (recur next-pos (subs directions 1) (update-in houses [(str x "," y)] (fnil inc 0))))))

(defn -main []
  (let [start-pos [0 0]
        houses {"0,0" 1}
        url "http://adventofcode.com/day/3/input"
        cookie {"session"
                {:value
                 "53616c7465645f5f490205ef4e103396927bbab128faab607f8179f95e2315a72870523a2dc765b0e0046be25f637978"}}]
  (deliver-gifts start-pos (get-input url cookie) houses)))
