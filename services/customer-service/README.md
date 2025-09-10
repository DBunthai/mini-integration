./gradlew spotlessApply	Auto-formats all matching files (based on target)
./gradlew spotlessCheck	Fails if any file violates formatting rules (used in CI)

🧪 Build Integration
Command	Description
./gradlew check	Runs all project checks (including spotlessCheck if you added dependsOn)
./gradlew build	Runs full build; fails if spotlessCheck fails (if wired into check)

--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED


Docker run

Build

./gradlew jibDockerBuild

Run 

```shell
    docker run --rm --name customer-service -p 8080:8080 --net=host  \
    -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/customer \
    -e SPRING_DATASOURCE_USERNAME=mini \
    -e SPRING_DATASOURCE_PASSWORD=mini1234 \
    -e SPRING_JPA_SHOW_SQL=true \
    -e SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true \
    -e SPRING_JPA_PROPERTIES_HIBERNATE_JDBC_TIME_ZONE=America/New_York \
    -e SPRING_JACKSON_TIME_ZONE=America/New_York \
    -e SPRING_DATA_REDIS_SENTINEL_MASTER=mymaster \
    -e SPRING_DATA_REDIS_SENTINEL_NODES=host.docker.internal:26379,host.docker.internal:26380 \
    -e SPRING_DATA_REDIS_TIMEOUT=5000 \
    -e SPRING_LIQUIBASE_ENABLED=true \
    -e SPRING_KAFKA_BOOTSTRAP_SERVERS=host.docker.internal:9092,host.docker.internal:9192,host.docker.internal:9292 \
    -e JAVA_TOOL_OPTIONS="-Duser.timezone=America/New_York" \
    customer-service:latest
```
