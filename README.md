# Dockerizar uma aplicação Spring Boot que utiliza Maven, Postgres e Kafka  

Projeto foi criado com o objetivo de mostrar a dockerização de dois micro serviços Spring Boot e a comunicação entre eles utilizando o Apache Kafka.


### 📋 Pré-requisitos

- Apache Kafka
- Docker

### 🔧 Instalação

- git clone https://github.com/masterulisses/portifolio
- Alterar variáveis no arquivo ../produto-ms/.env
   - host: Informar "localhost" se estiver usando mac ou linux, caso esteja usando linux via wsl informar o "ip" do linux. Comando para pegar o ip (ip addr show eth0 | grep -oP '(?<=inet\s)\d+(\.\d+){3}'
)  
    - server_kafka: alterar "172.20.69.204" para o valor informado na variavel host.
    - dir_venda_ms: Informar o diretório do projeto venda-ms
      
- cd /home/user/produto-ms
- docker compose up --build


## ⚙️ Executando os testes 
- Observação: Se estiver usando wsl, informar o ip do linux no lugar de localhost
- Listar e testar os endpoints do micro serviço produto-ms
  - http://localhost:8082/produto-ms/swagger-ui/index.html 
- Listar e testar os endpoints do micro serviço venda-ms
  - http://localhost:8081/venda-ms/swagger-ui/index.html
- Como ver se a comunicação entre os micro serviços foi realizada.
   - Usar o endpoint "Incluir novo produto" do projeto produto-ms
   - Depois utilizar o endpoint "Consultar Produto por id" e informar "1" para o parâmetro "id". Se retornar o produto incluído no endpoint anterior, ocorreu tudo certo.
 - Debugar o container da aplicação venda-ms
    - Crie um breakpoint no método "consumer" da classe "KafkaConsumerProduto"
    - Crie um servidor "remoto" no IntelijIdea e informe o ip do linux em host caso esteja utilizando wsl.
    - Observações:
        - Já foi exposta a porta 5005 no docker compose para a aplicação venda-ms
        - Todos os parâmetros necessários para a JVM já são preenchidos pelo IntelijIdea de forma automática como é o caso de "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005)"
    - Execute o servidor remoto e faça uma inclusão de um novo produto através do micro serviço produto-ms
## 🛠️ Construído com

* Java8
* Spring boot
* Maven
* Docker
* Kafka
* Swagger
* Bean Validation
* Hibernate
* Postgres
* Lombok
* Paginação em banco
