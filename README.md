Credit to the original issue reporter @heuristicservices in https://github.com/springdoc/springdoc-openapi/issues/2058. 

In `application.yml` we have `servlet.forward-headers-strategy: framework` defined so that our app processes the incoming `X-Forwarded-*` from our reverse proxy, like it's suggested in springdoc's 2.x FAQ.

In our case we defined the following config to show the problematic redirect behaviour more clearly:

```
springdoc:
  api-docs:
    path: /swagger/api-docs
  swagger-ui:
    path: /swagger/swagger-ui.html
```

Generate Spring Boot Application jar

    mvn clean install

Generate Java and nginx containers

    docker-compose build

Demonstration of bug:

1. Run Spring Boot application jar from local machine: `java -jar target/springdoc-context-path-1.0-SNAPSHOT.jar`

2. Navigate to `http://localhost:8080/petstore/swagger/swagger-ui.html` to view petstore's swagger-ui, all resources are loaded correctly since it redirects the initial request to the expected url `http://localhost:8080/petstore/swagger/swagger-ui/index.html`.

3. Stop application and run `docker compose up` which uses nginx `X-Forwarded-Prefix` header with fixed `/api` value, just for demo purposes.

4. Navigate to `http://localhost:8080/api/petstore/swagger/swagger-ui.html`. The `swagger-ui.html` should redirect to `http://localhost:8080/api/petstore/swagger/swagger-ui/index.html`, taking into account the passed `X-Forwarded-Prefix` and the servlet context path.
Instead we're redirected to `http://localhost:8080/api/swagger/swagger-ui/index.html`, ommiting the servlet context path entirely.