### Compilation
```
javac -cp "JUnit/*" myAdapter/*.java myTest/*.java
```

### Running all Tests
```
java -cp ".:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myTest.TestRunner
```

### Documentation
```
javadoc -d docs -cp ".:junit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myAdapter myTest
```