# Trabalho-APSOO  
  Trabalho em grupo desenvolvido para a matéria de Análise e Projeto de Software Orientado a Objetos da UFMS com base em documentos/artefatos criados pelos integrantes do grupo  

---  

## Integrantes:  
- Lucas Gonçalves Cordeiro  
- Marco Aurélio Allaman  
- Larryssa Nex Spica  
- Kélvisck Aureliano Cabral  

---  

## Resumo sobre o Sistema:  

  &nbsp; Esse projeto é um Sistema de Locação de Artigos para festa, sua principal funcionalidade inclui registrar uma nova locação baseada em uma lista de artigos escolhidos pelo funcionário para um cliente além do registro do pagamento realizado pelo cliente.  

---  
---  

# Detalhes sobre `Classes` e `Relacionamentos` (sem FK)  

## `Pessoa`  
### Atributos:  
 - Nome - String - varchar(100)  
 - CPF - String - char(11)  

 > CPF deve ser validado na aplicação exceto por sequências de um único digito  

## `Cliente` herda Pessoa  
### Atributos:  
 - Celular - String - char(11)  

## `Funcionario` herda Pessoa  
### Atributos:  
 - Senha - String - char(256)  

 > Senha deve ser salva como hash SHA3-256 no banco de dados  

## `Locacao`  
### Atributos:  
 - Id - int - int  
 - inicioPrevisto - Date - date  
 - fimPrevisto - Date - date  
 - dataReservada - Date - date  
 - endereco - String - varchar(255)  

## `Artigo`  
### Atributos:  
 - Codigo - int - int  
 - Nome - String - varchar(100)  
 - ValorDiario - double - double  
 - EstoqueTotal - int - int  

## `ArtigoLocado`  
### Atributos:  
 - Id - int - int  
 - Quantidade - int - int  
 - valorCotado - double - double  
 - valorTotal - double - double  

---  

# Configurations  

O arquivo `config.properties` tem os seguintes atributos padrão    
- DB_USER=postgre  
- DB_PASS=postgre  
- DB_NAME=G10_APSOO  
- DB_HOST=localhost  
- DB_PORT=5432  
  
O arquivo `MANIFEST.MF` tem todas as configurações para lançar um .jar incluindo dependencias  

Class-Path: lib/postgresql-42.3.5.jar  
