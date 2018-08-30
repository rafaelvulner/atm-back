# Sumário

Trata-se de uma aplicação que simula a entrega de notas quando um cliente efetua um saque em um caixa eletrônico. 

## Detalhes da API RESTful

A API RESTful de simulação de um caixa eletrônico contém as seguintes características:

Projeto criado com Spring Boot e Java 8
Banco de dados MySQL e H2 com JPA e Spring Data JPA
Respostas utilizando ResponseEntity


## Como Executar a aplicação

Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git.

git clone https://github.com/rafaelvulner/atm-back.git
cd java-interview
mvn spring-boot:run

Importando o projeto no Eclipse ou STS
No terminal, execute a seguinte operação:

mvn eclipse:eclipse
No Eclipse/STS, importe o projeto como projeto Maven.

Acesse os endpoints através da url http://localhost:8080

Segue os endpoints configurados:

#ENDPOINT BANK
http://localhost:8080/bank METHOD.GET (Retorna todos os bancos cadastrados)

http://localhost:8080/bank METHOD.POST (Cadastra um novo banco)
{"id":0 "name": "Itau"}

#ENDPOINT ACCOUNT
http://localhost:8080/account METHOD.GET (Retorna todos as contas cadastradas)

http://localhost:8080/account METHOD.POST (Cadastra uma nova conta)
{
	"id":0,
	"number": "3035",
	"password": "12345",
	"owner": "Rafael Elias Monteiro Lima",
	"bank": {"id":1, "name": "Santader"},
	"balance": "4200"
}

http://localhost:8080/account METHOD.PUT (Atualiza a conta)
{	
	"number": "3035"
	"password": "12345",
	"owner": "Rafael Elias Monteiro Lima",
	"bank": {"id":2, "name": "Itau"},
	"balance": "4200"
}

http://localhost:8080/account METHOD.DELETE (Exclui uma conta)
{	
	"number": "3035"
	"password": "12345",
	"owner": "Rafael Elias Monteiro Lima"
}

#ENDPOINT ATM-TERMINAL
http://localhost:8080/atm-api/deposit METHOD.PUT (Atualiza uma conta com o valor do deposito)
{
	"bankName": "santander",
	"accountNumber": "2021",
	"password": "12345",
	"balance": "5000"
}

http://localhost:8080/atm-api/withdraw METHOD.PUT (Efetua o saque em uma conta existente)
{
	"bankName": "santander",
	"accountNumber": "2021",
	"password": "12345",
	"balance": "100"
}

http://localhost:8080/atm-api/balance METHOD.POST (Verifica o saque através do numero e senha)
{
	"bankName": "santander",
	"accountNumber": "2021",
	"password": "12345",
	"balance": "100"
}


