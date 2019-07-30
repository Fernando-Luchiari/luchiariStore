# luchiariStore
Segue especificações tecnicas da aplicação:


 - SpringBoot 2.1.6
 - Java 12
 - Maven
 - Banco de dados H2
 - JPA


fora criados serviços de CRUD conforme solicitado e mais um serviço para recuperar as campanhas válidas pelo id do time.

A aplicação ja se inicia com dois times cadastrados

rota inicial local /api/v1

Campanhas 

GET /campanhas


GET /campanhas/{id}
long :id

POST /campanhas

DELETE /camapanhas/{id}

PUT /campanhas/{id}

GET /campanhas/timeCoracao/{idTimeCoracao}
================================================================
Time	

GET /times

POST /time

GET /time/{idTime}

================================================================

Socio Torcedor 

POST /socioTorcedor


================================================================

