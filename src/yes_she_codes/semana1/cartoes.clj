(ns yes-she-codes.cartoes)

(def dados-cartao [
                   [1234123412341234 111 "2023-01" 1.000 "000.111.222-33"]
                   [4321432143214321 222 "2024-02" 2.000 "333.444.555-66"]
                   [1598159815981598 333 "2021-03" 3.000 "666.777.888-99"]
                   [6655665566556655 444 "2025-04" 4.000 "666.777.888-99"]
                   [3939393939393939 555 "2026-05" 5.000 "999.123.456-78"]
                   ])

(defn novo-cartao [numero cvv validade limite cpf]
  {:numero numero :cvv cvv :validade validade :limite limite :cliente cpf})

(defn modela-cartao [dados]
  (vec (map (fn [[numero cvv validade limite cliente]]
         (novo-cartao numero cvv validade limite cliente))
       dados)))

(defn lista-cartoes []
  (modela-cartao dados-cartao))

(println (lista-cartoes))