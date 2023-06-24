# Dockerizar duas aplicações Spring Boot que utiliza Maven, Postgres e Kafka  

Projeto foi criado com o objetivo de mostrar a dockerização de dois micro serviços Spring Boot e a comunicação entre eles utilizando o Apache Kafka.


### 📋 Pré-requisitos

- Git
- Docker

### 🔧 Instalação

- cd /home/user
- git clone https://github.com/masterulisses/portifolio
- cd /home/user/portifolio/produto-ms
- docker compose up --build
- Repositorio "produto-ms"
    - Objetivo: Cadastrar os produtos para que seja vendido no micro serviço venda-ms
    - Tem seu próprio banco de dados postgres.
    - EndPoints:
        - Consultar Produto por id: Consulta um produto pelo id
        - Consultar Produto pela descricaoCurta: Consulta um produto pela descrição, e retorna o dado paginado.
        - Incluir novo produto: Incluir um novo produto
        - Alterar produto: Alterar um produto já cadastrado
        - Excluir produto: Exclui um produto já cadastrado
- Repositorio "venda-ms"
    - Responsável por realizar a venda de produtos
    - Tem seu próprio banco de dados postgres.
    - EndPoints:
        - Consultar Produto por id: Consulta um produto pelo id
        - Consultar Produto pela descricaoCurta: Consulta um produto pela descrição, e retorna o dado paginado.
        - Incluir nova venda de produto: Incluir uma nova venda de produtos para uma pessoa.
        - Excluir venda de produto: Excluir uma venda já realizada.
- Alterar variáveis no arquivo ../produto-ms/.env
   - host: Informar "localhost" se estiver usando mac ou linux, caso esteja usando linux via wsl informar o "ip" do linux. Comando para pegar o ip "(ip addr show eth0 | grep -oP '(?<=inet\s)\d+(\.\d+){3}'
)"  
   - dir_venda_ms: Informar o diretório do projeto venda-ms
      


## ⚙️ Executando os testes 
- Observação: Se estiver usando wsl, informar o ip do linux no lugar de localhost
- Listar e testar os endpoints do micro serviço produto-ms usando o swagger
  - http://localhost:8082/produto-ms/swagger-ui/index.html 
- Listar e testar os endpoints do micro serviço venda-ms usando o swagger
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
* Postgres
* Swagger
* Bean Validation
* Hibernate
* Lombok
* Paginação em banco

## ✒️ Autor

- Ulisses Silva de Souza
- masterulisses@gmail.com.br
- 62-9 8155-3380
