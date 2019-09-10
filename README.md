# Kotlin Test Task

* Create a service to search for GitHub users by the programming language they use in their public repositories. The service can be either a REST API with a single endpoint, or simple web UI that obtains displays required information.
* Each user returned in the response of the search request should at least contain the username, name, avatar url and number of followers.
* Use the GitHub APIs (https://developer.github.com/v3/) to retrieve the information.
* The service could be developed with a technology that you feel comfortable with, but we would prefer Spring Boot/Kotlin for backend, or React for the frontend part (if any). Feel free to use the libraries you find suitable for this task.
* The service should be covered with tests you find suitable for this task.
* Create a Dockerfile to run the service.
* Please use Git for this project. When you're done please create a zip-file of your working directory.
* The goal isn't to build a ready product, but rather to get an idea of your skills. Please do not spend much time on this task, just let us know how much time you spent.

## How to run

* Build and run docker image
```bash 
 docker build -t kotlin_test_task .
 docker run -d -it -p 80:8080 --name=ktt kotlin_test_task
 ```
 
 * Open [http://localhost/swagger-ui.html/](http://localhost/swagger-ui.html) for inspecting API

## How to run test

TODO