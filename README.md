#### Net Calculator Service

Service allows consumers to calculate the net price from a gross price.

Service interface:
[NetCalculatorService](src/main/java/com/symulakr/netcalculator/service/NetCalculatorService.java)

Default implementation:
[DefaultNetCalculatorService](/src/main/java/com/symulakr/netcalculator/service/DefaultNetCalculatorService.java)

Features:
- Tax Rate validation
- configurable precision of rounding 

[Configuration properties](src/main/resources/application.yml)

How it works:
- clone this repository.
- run maven command `mvn spring-boot:run` 
  or use maven wrapper `./mvnw spring-boot:run` to run application
- on localhost:8080 you will see a simple Swagger UI that allows you to review the HTTP methods and test them.

Also you can build a docker image with this service by build-in spring-boot plugin:

`mvn spring-boot:build-image`

and run it

`docker run -it -p8080:8080 net-calculator:0.0.1-SNAPSHOT`
