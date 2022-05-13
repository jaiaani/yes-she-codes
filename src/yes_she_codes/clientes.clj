(ns yes-she-codes.clientes)

(def dados-clientes [
                     ["Feiticeira Escarlate"	"000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
                     ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"]
                     ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"]
                     ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"]
                     ])

(defn novo-cliente [nome cpf email]
  { :nome nome :cpf cpf :email email})

(defn modela-cliente [dados]
  (vec (map (fn [[nome cpf email]]
         (novo-cliente nome cpf email) )
       dados)))

(defn lista-clientes []
  (modela-cliente dados-clientes))

(println (lista-clientes))