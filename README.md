[![Build Status](https://drone.io/github.com/oneils/html4email/status.png)](https://drone.io/github.com/oneils/html4email/latest)

## html4email-kotlin  ##
An application for generating Digest email template from JSON file.


### To build backend:  ###

    # ./gradlew build

or if _Gradle_ is installed

    # gradle build

### To start:  ###

    # java -jar build\libs\html4email-kotlin.jar

or

    # ./gradlew bootRun


By default application uses embedded database *HSQLDB*. Embedded database could be switched to PostgreSQL with the
following environment variable 'spring.config.name':
 ```
 spring.config.name=application-prod
 ```

### REST api documentation: ###

* Via Swagger UI:
<http://localhost:8080/swagger-ui.html>

* Via JSON:
<http://localhost:8080/v2/api-docs>
