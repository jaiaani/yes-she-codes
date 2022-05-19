(ns yes-she-codes.compras)

(def dados-compras [
                    ["2022-01-01" 129.90 "Outback" "Alimentação" 1234123412341234]
                    ["2022-01-02" 260.00 "Dentista" "Saúde" 1234123412341234]
                    ["2022-02-01" 20.00 "Cinema" "Lazer" 1234123412341234]
                    ["2022-01-10" 150.00 "Show" "Lazer" 43214321 43214321]
                    ["2022-02-10" 289.99 "Posto de gasolina" "Automóvel" 4321432143214321]
                    ["2022-02-20" 79.90 "iFood" "Alimentação" 4321432143214321]
                    ["2022-03-01" 85.00 "Alura" "Educação" 4321432143214321]
                    ["2022-01-30" 85.00 "Alura" "Educação" 15981598 15981598]
                    ["2022-01-31" 350.00 "Tok&Stok" "Casa" 1598159815981598]
                    ["2022-02-01" 400.00 "Leroy Merlin" "Casa" 1598159815981598]
                    ["2022-03-01" 50.00 "Madero" "Alimentação" 6655665566556655]
                    ["2022-03-01" 70.00 "Teatro" "Lazer" 6655665566556655]
                    ["2022-03-04" 250.00 "Hospital" "Saúde" 6655665566556655]
                    ["2022-04-10" 130.00 "Drogaria" "Saúde" 6655665566556655]
                    ["2022-03-10" 100.00 "Show de pagode" "Lazer" 3939393939393939]
                    ["2022-03-11" 25.90 "Dogão" "Alimentação" 3939393939393939]
                    ["2022-03-12" 215.87 "Praia" "Lazer" 3939393939393939]
                    ["2022-04-01" 976.88 "Oficina" "Automóvel" 3939393939393939]
                    ["2022-04-10" 85.00 "Alura" "Educação" 3939393939393939]])

(defn nova-compra [data valor estabelecimento categoria cartao]
  {:data data :valor valor :estabelecimento estabelecimento :categoria categoria :cartao cartao})

(defn modela-compra [dados]
 (vec (map (fn [[data valor estabelecimento categoria numero-cartao]]
         (nova-compra data valor estabelecimento categoria numero-cartao))
       dados)))

(defn lista-compras []
  (modela-compra dados-compras))


(def compras (lista-compras))

; {:data data :valor valor :estabelecimento estabelecimento :categoria categoria :cartao cartao}

;Calcular o total gasto em compras de um cartão
;Criar a função total-gasto, que recebe uma lista de compras e retorna a soma dos valores gastos.


(defn total-gasto [lista-de-compras]
  (->> lista-de-compras
       (map :valor)
       (reduce +)))

;Criar uma função que, dado um mês e uma lista de compras, retorne uma lista de compras feitas somente naquele mês
(defn get-mes [data]
  (str (get data 5) (get data 6)))

(defn filtra-por-mes [mes lista-de-compras]
  (filter #(= (get-mes(:data %)) mes) lista-de-compras))

;Criar a função total-gasto-no-mes, que calcule a soma dos valores gastos num determinado cartão em um mês.
;Para facilitar, considere que todas as compras consideradas sejam de um mesmo cartão.

(defn total-gasto-no-mes [mes lista-de-compras]
  (total-gasto (filtra-por-mes mes lista-de-compras)))



;Criar uma função que, dado um estabelecimento e uma lista de compras,
; retorne uma lista de compras feitas somente naquele estabelecimento.

(defn filtra-por-estabelecimento [estabelecimento lista-de-compras]
  (filter #(= (:estabelecimento %) estabelecimento) lista-de-compras))

;(println compras)
;(println "\n\n\n" (total-gasto compras))
;(println "\n\n\n" (filtra-por-mes "01" compras))
;(println "\n\n\n" (total-gasto-no-mes "01" compras))
;(println "\n\n\n" (filtra-por-estabelecimento "Outback" compras))

;Criar uma função que retorne as compras que estão dentro de um intervalo de valor máximo e valor mínimo.

(defn compras-entre-min-max [valor-minimo valor-maximo compras]
  (filter #(and (>= (:valor %) valor-minimo) (<= (:valor % )valor-maximo))compras))

;(println (compras-entre-min-max 40 100 compras))

;Criar uma função que retorne os total gasto **agrupados por categoria**.

(defn agrupa-por-categoria [compras]
  (vec (map (fn [[categoria compras-da-categoria]]
              {:categoria   categoria
               :total-gasto (total-gasto compras-da-categoria)})
            (group-by :categoria compras))))

(println (agrupa-por-categoria compras))



