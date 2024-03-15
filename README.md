# vizen-svc

## Application Properties 
add application.properties file in the project root folder with below content
and replace place holders with valid values
```
JWT_SECRET_KEY=<Key if needed(optional for now)>
JWT_TTL_MINS=180
DB_SERVER=<valid_db_server_name>
DB_PORT=<db port>
DB_SCHEMA=<schma_name>
DB_USE_SSL=true
DB_REQUIRE_SSL=true
DB_USER=<DB_user_name>
DB_PASSWORD=<DB_password>

EMAIL_FROM=<from_email>
EMAIL_USER_NAME=<from_user_name>
EMAIL_USER_PWD=<email_pwd>
EMAIL_HOST=<email_Host>
EMAIL_PORT=<emap_port>
AWS_ACCESS_KEY=<access_key>
AWS_SECRET_KEY=<access_secret>
AWS_ACCOUNT_ID=<aws_act>
AWS_USER_ARN=<arn_url>
```

./gradlew clean build

Update application.properties
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar

http://localhost:8080/swagger-ui.html
