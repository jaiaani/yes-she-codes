(ns yes-she-codes.semana4.utils)


(defn processa-csv [caminho-arquivo]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (mapv #(clojure.string/split % #","))))