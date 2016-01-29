[![Build Status](https://drone.io/github.com/oneils/html4email/status.png)](https://drone.io/github.com/oneils/html4email/latest)

## html4email-kotlin  ##
Currently is a prototype of the application for generating Digest email template from JSON file.

### To build backend:  ###

    # ./gradlew build

or if _Gradle_ is installed

    # gradle build

### To start:  ###

    # java -jar build\libs\html4email-kotlin.jar

or

    # ./gradlew bootRun

### REST api documentation: ###

* Via Swagger UI:
<http://localhost:8080/swagger-ui.html>

* Via JSON:
<http://localhost:8080/v2/api-docs>

## How to build UI

#### Install required libs for Grunt:
```
npm install -g grunt-cli
npm install  grunt-cli --save-dev
npm install grunt-contrib-jshint --save-dev
npm install jshint-stylish --save-dev
npm install time-grunt --save-dev
npm install jit-grunt --save-dev
npm install grunt-contrib-copy --save-dev
npm install grunt-contrib-clean --save-dev
npm install grunt-contrib-concat --save-dev
npm install grunt-contrib-cssmin --save-dev
npm install grunt-contrib-uglify --save-dev
npm install grunt-filerev --save-dev
npm install grunt-usemin --save-dev
npm install grunt-contrib-watch --save-dev
npm install grunt-contrib-connect --save-dev
```

After that run the command:
```
bower install
```

### To build UI run the command
```
grunt build --force
```
