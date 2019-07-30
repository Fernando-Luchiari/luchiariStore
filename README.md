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


Para solicionar o problema de reorganização das campanhas foram criados dois metodos no campanhaService:

reorganizaVigencia - esse metodo ajusta a data da ultima vigencia cadastrada no banco que seja igual o menor que a data de vigencia da nova campanha, apos esse ajuste chamamos o reordenaCampanhasAntigas , que falarei mais abaixo.

reordenaCampanhasAntigas- esse metodo recursivo reorganiza a data das campanhas menores ou iguais que a ultima campanha cadastrada no banco com data de criação menor ou igual a ela.


#########Os teste unitários não foram finalizados############


