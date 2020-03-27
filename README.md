## How to run application on local

- Checkout this repo on local from https://github.com/vikramwable/hrms.git
- Setup postgres database on local machine and apply script src/main/resources/sql-scripts/db-setup-script.sql 
- Open Application.java file and run 
- Goto URL http://localhost:8020/hrms/swagger-ui.html

## Code style recommendation
- Use following code style `code-style-intellij-version-1.0.0.xml` present in root directory.
- Import this code style at project level in Intellij IDEA.
- To know more about how to import, please refer https://www.jetbrains.com/help/idea/copying-code-style-settings.html

## SonarLint recommendation
- Install SonarLint plugin in your Intellij IDEA
- It suggests any code smells if any to developer.
- Before committing your code, make sure that there are not SonarLint code smell warnings and if any your are fixing those.
- Plugin can be found here: https://plugins.jetbrains.com/plugin/7973-sonarlint

## Model design recommendation
- Whenever new model is introduced, make sure that it is extending `BaseModel`
- This way it will inherit common functionality such as JPA auditing.

## RestController design recommendation
- Follow RESTful services naming convention
- Do not use custom response object. Leverage Spring Boot `RestController`. 
- Thus you can return model objects directly from rest controller methods.
- Spring Boot will handle response consistently for all APIs.
- Use `@ResponseStatus` as per REST convention for all methods.

## REST Exception handling design recommendation
- Leverage Spring Boot and do not send custom response using `@ExceptionHandler`
- Spring Boot will handle response consistently for all exceptions.
- Annotate custom exception classes with relevant `@ResponseStatus`.