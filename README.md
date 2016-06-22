[![Build Status](https://drone.io/github.com/oneils/html4email/status.png)](https://drone.io/github.com/oneils/html4email/latest)

## html4email-kotlin  ##
Currently this is a prototype of the application for generating Digest email template from JSON file.

Supported Json format for importing from:
```
{
  "title": "Digest #4",
  "contributeTo": "your_company@mail.com",
  "topics": [
    {
      "topic": "NEWS",
      "articles": [
        {
          "title": "First NEWS title",
          "description": "First article NEWS Description",
          "url": "http://localhost/articles/first"
        },
        {
          "title": "Second NEWS title",
          "description": "Second article NEWS Description",
          "url": "http://localhost/articles/second"
        }
      ]
    },
    {
      "topic": "PEOPLE",
      "articles": [
        {
          "title": "First PEOPLE title",
          "description": "First article PEOPLE Description",
          "url": "http://localhost/people/first"
        },
        {
          "title": "Second PEOPLE title",
          "description": "Second article PEOPLE Description",
          "url": "http://localhost/people/second"
        }
      ]
    }
  ]
}
```

### To build backend:  ###

    # ./gradlew build

or if _Gradle_ is installed

    # gradle build

### To start:  ###

    # java -jar build\libs\html4email-kotlin.jar

or

    # ./gradlew bootRun


Application uses **MongoDB** database.

database name: **digest**

collections:
- digests - collection with published Digests
- articles - collection with artciles which could be used for new Digests.

### REST api documentation: ###

* Via Swagger UI:
<http://localhost:8080/swagger-ui.html>

* Via JSON:
<http://localhost:8080/v2/api-docs>

## How to build UI

#### Install Bower and Grunt:
```
npm install -g grunt-cli
npm install -g bower
```
Go to the `/app-src`

Run the commands:

`npm instal` (to install npm dependencies)

`bower install` (to install bower modules)


### To build UI run the command
```
grunt build --force
```
