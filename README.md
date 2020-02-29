## How to run application on local

- Checkout this repo on local from https://github.com/vikramwable/hrms.git
- Setup postgres database on local machine and apply script src/main/resources/sql-scripts/db-setup-script.sql 
- Open Application.java file and run 
- Goto URL http://localhost:8020/hrms/swagger-ui.html


## Model design recommendation
- Whenever new model is introduced, make sure that it is extending `BaseModel`
- This way it will inherit common functionality such as JPA auditing.