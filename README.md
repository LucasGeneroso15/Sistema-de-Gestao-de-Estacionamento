# Sistema de Gestão de Estacionamento

Repositório do desafio "Sistema de Gestão de Estacionamento" do Programa de Bolsas da Compass Uol.


### AWS-SPRINGBOOT_AGO2024.
## Requisitos

Certifique-se de ter as seguintes ferramentas instaladas:
- Java Development Kit (JDK) 11 ou superior
- Apache Maven ou Gradle
- mysql-connector-java 8.0.28
- Alterar arquivo "db.properties" para conexão com o banco de dados 

## Instalação
Siga os passos abaixo para configurar o projeto no seu ambiente
1. **Clone o repositório**
 ```bash
 git clone https://github.com/LucasGeneroso15/Sistema-de-Gestao-de-Estacionamento.git
 cd Sistema-de-Gestao-de-Estacionamento
 ```

2. **Compile o projeto**

 ```bash
 mvn clean install
 ```

3. **Execute o projeto**
   Para executar o projeto, use o comando:

 ```bash
 mvn exec:java -Dexec.mainClass="app.Program"
 ```
## Tabelas Banco de Dados

As tabelas do banco de dados foram criadas de acordo com anexo "tabelas_bd"

## Testes Aplicação

Para a realização dos testes foi utilizado os scripts em sql de acordo com o anexo "scripts.md"

No arquivo consta os scripts para a inserção de linhas na tabela "vagas".


### Contato

Para dúvidas ou problemas, entre em contato com:
* Nome: Lucas Generoso
* Email: lucas.generoso.pb@compasso.com.br
* GitHub: github.com/LucasGeneroso15