[![Build Status](https://gitlab.com/oneils/html4email/badges/master/build.svg)](https://gitlab.com/oneils/html4email/badges/master/build.svg)

## html4email
Currently this is a prototype of the application for generating Digest email template from JSON file.

Supported `JSON` format for importing from:
```
{
  "title": "Digest #4",
  "contributeTo": "your_company@mail.com",
  "companyName": "Your Company Name",
  "publishedDate": 1476252979000,
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

Generated template will look into the following way:
![idgst](https://gitlab.com/oneils/html4email/uploads/9796ee9235c237155617a9ad2e2f648f/idgst.PNG)

Template is ready for sending via email.

## How to build UI

Node.js and NPM should be already installed.

How to install Node.js see official documentation: https://nodejs.org/en/download/package-manager/

#### Install Bower and Grunt:
```
npm install -g grunt-cli
npm install -g bower
```  
Go to the `/web-src`

Run the commands:

`npm instal` (to install npm dependencies)

`bower install` (to install bower modules)


### To build UI run the command
```
grunt build --force
```

## To build backend:

`./gradlew build`

or if `Gradle` is installed

`gradle build`

### To start:  ###

`java -jar build\libs\html4email-x.y.z.jar.jar`
where `x.y.z` - project version

or

`./gradlew bootRun`  


### Generate preview
1. Run application

2. Go to http://localhost/#/import-digest

3. Specify `JSON` file with Digest content

4. Click on `Upload`


### REST api documentation: ###

* Via Swagger UI:
<http://localhost:8080/swagger-ui.html>

* Via JSON:
<http://localhost:8080/v2/api-docs>

#### Database
Application uses **MongoDB** database.

database name: **digest**

collections:
- digests - collection with published Digests
- articles - collection with artciles which could be used for new Digests.
