# Software Engineering Project: MapAdapter
This project implements the MapAdapter class, which adapts the Hashtable class to the HMap interface. It provides a full implementation of the Map interface as specified in J2SE 1.4.2, including all optional operations. The backing data structure is a CLDC 1.1 Hashtable.

### Requirements
- **Java Development Kit (JDK)**: Required to compile and run the code.

### Compilation
```
javac -cp "JUnit/*" myAdapter/*.java myTest/*.java
```

### Running all Tests 
- **Unix**
```
java -cp ".:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myTest.TestRunner
```
- **Windows**
```
java -cp ".;JUnit/junit-4.13.2.jar;JUnit/hamcrest-core-1.3.jar" myTest.TestRunner
```

### Single Test Run 
- **Unix**
```
java -cp ".:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterCoreTest
java -cp ".:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterNullConstraintTest
java -cp ".:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterEqualsHashCodeTest
java -cp ".:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterKeySetTest
java -cp ".:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterValuesTest
java -cp ".:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterEntrySetTest
java -cp ".:JUnit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterIteratorTest
```
- **Windows**
```
java -cp ".;JUnit/junit-4.13.2.jar;JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterCoreTest
java -cp ".;JUnit/junit-4.13.2.jar;JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterNullConstraintTest
java -cp ".;JUnit/junit-4.13.2.jar;JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterEqualsHashCodeTest
java -cp ".;JUnit/junit-4.13.2.jar;JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterKeySetTest
java -cp ".;JUnit/junit-4.13.2.jar;JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterValuesTest
java -cp ".;JUnit/junit-4.13.2.jar;JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterEntrySetTest
java -cp ".;JUnit/junit-4.13.2.jar;JUnit/hamcrest-core-1.3.jar" myTest.MapAdapterIteratorTest
``` 

### Documentation
- **Unix**
```
javadoc -d docs -cp ".:junit/junit-4.13.2.jar:JUnit/hamcrest-core-1.3.jar" myAdapter myTest
```
- **Windows**
```
javadoc -d docs -cp ";junit/junit-4.13.2.jar;JUnit/hamcrest-core-1.3.jar" myAdapter myTest
```