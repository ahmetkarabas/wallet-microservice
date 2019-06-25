# Wallet Microservice

A simple wallet microservice running on the JVM that manages credit/debit transactions on behalf of players.

## Getting Started

An implementation of transaction rest api that external callers
can make credit or debit on a player account with globally unique transaction id.

### Prerequisites

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the 'main' method in the 'com.ahmet.wallet.WalletApplication' class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like this:

```shell
mvn spring-boot:run
```
Then application can be started successfully.

You can manage the api service with swagger as below link:</br>
	http://localhost:8080/api/wallet/swagger-ui.html</br>
Also you can see H2 DB as below link:</br>
	http://localhost:8080/api/wallet/h2-console
