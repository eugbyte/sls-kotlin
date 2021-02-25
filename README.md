# aws-kotlin-jvm-gradle

AWS Lambda with Kotlin, Java 11, and Gradle 6.0.  
Complete with Data Layer (DynamoDb), Service Layer, dependency injection and middleware

## build

`./gradlew clean build`

## deploy

`./gradlew deploy`

## useful commands
`sls invoke local -f create --data '{"body": {"question":"What is your name", "answer": "Tom" } }'`  
`sls invoke local -f get --data '{ "pathParameters": {"id": "2ec69e85-4313-4995-ba8a-ffc7f5f6ed8b" } }'`