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