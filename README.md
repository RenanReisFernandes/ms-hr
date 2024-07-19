
<h1 align="center">
 MICROSERVIÇO DE RH
 <h3 align ="center" >Projeto de implementação de microserviços java ☕</h3> 
</h1>

### 📕 SOBRE 
**Microserviço de Funcionários (hr-worker)**: Gerencia informações dos funcionários, incluindo ID, agência, conta, CPF, nome, endereço e renda diária.

**Microserviço de RH (hr-payroll)**: Consulta o microserviço de funcionários para buscar os dados de um funcionário específico e calcula o rendimento com base na renda diária e na quantidade de dias trabalhados.

### 🔨 FERRAMENTAS UTILIZADAS

- [**JAVA**](https://docs.oracle.com/en/java/)
- [**SPRING BOOT**](https://docs.spring.io/spring-boot/index.html)
- [**SPRING SECURITY**](https://docs.spring.io/spring-security/reference/index.html)
- [**SPRING CLOUD**](https://spring.io/projects/spring-cloud)
- [**GIT**](https://git-scm.com/doc)
- [**GITHUB**](https://docs.github.com/pt)
- [**H2**](https://www.h2database.com/html/main.html)
- [**SWAGGER**](https://swagger.io/docs/)
- [**POSTMAN**](https://learning.postman.com/docs/introduction/overview/)

### 🦾 INSTALANDO O PROJETO NA SUA MÁQUINA
```
# CLONE O PROJETO
$ git clone https://github.com/seu-usuario/seu-repositorio.git
```

```
# INSTALE AS DEPENDÊNCIAS COM SUA IDE NO PROMPT DÊ O COMAANDO (CASO VOCÊ USE NPM)
$ npm install
```

```
# INSTALE AS DEPENDÊNCIAS COM SUA IDE NO PROMPT DÊ O COMAANDO (CASO VOCÊ USE YARN)
$ yarn
```

```
# EXECUE OS MICROSERVIÇOS
$ mvn spring-boot:run
```

### 👁‍🗨 OS MICROSERVIÇOS ESTARÃO DISPONÍVEIS NAS SEGUINTES URLS

- **hr-worker**: http://localhost:8001/worker
- **hr-payroll**: http://localhost:8080/payments

#### LINKS PARA ACESSO DO SWAGGER
-[SWAGGER FUNCIONÁRIOS](http://localhost:8001/swagger-ui/index.html)

-[SWAGGER PAGAMENTOS](http://localhost:8101/swagger-ui/index.html)

#### IMAGENS ILUSTRATIVAS SWAGGER
<p>
 <img src ="hr-payroll\src\main\java\assets\imagens-microservicos\img-swagger-worker.PNG">
 </p><br>
 <p>
 <img src ="hr-payroll\src\main\java\assets\imagens-microservicos\img-swaggwe-payroll.PNG">
 </p><br>
 <p>
 <img src ="hr-payroll\src\main\java\assets\imagens-microservicos\img-postman.PNG">
 </p>

### 🚀  próximos passos

- IMPLEMENTAÇÃO DE AUTENTICAÇÃO E AUTORIZAÇÃO
- CONFIGURAÇÃO DE SERVIDOR
- BALANCEAMENTO DE CARGA
- IMPLEMENTAÇÃO DO GATEWAY
