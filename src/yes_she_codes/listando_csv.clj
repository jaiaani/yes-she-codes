(ns yes-she-codes.listando-csv)

(defn processa-csv [caminho-arquivo funcao-mapeamento]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))
       (println)
       (mapv funcao-mapeamento)
       ))

