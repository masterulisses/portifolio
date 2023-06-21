# Primeira etapa de construção: compilar o projeto com o Maven e empacotar em um JAR
FROM maven:3.8.4-openjdk-8-slim AS builder

# Copiar o código-fonte e o arquivo pom.xml para o contêiner
COPY src /app/src
COPY pom.xml /app/pom.xml

# Definir o diretório de trabalho para o Maven
WORKDIR /app

# Compilar o projeto com o Maven e empacotar em um JAR
RUN mvn clean package -DskipTests

# Segunda etapa de construção: criar o contêiner final com a imagem do OpenJDK
FROM openjdk:8-slim

# Definir o diretório de trabalho para o contêiner final
WORKDIR /app

# Copiar o arquivo JAR gerado pela etapa anterior para o contêiner final
COPY --from=builder /app/target/*.jar /app/app.jar

# Comando para executar a aplicação quando o contêiner for iniciado
ENTRYPOINT ["java", "-jar", "app.jar"]