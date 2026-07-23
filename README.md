# Software Engineering Project: MapAdapter
This project implements the MapAdapter class, which adapts the Hashtable class to the HMap interface. It provides a full implementation of the Map interface as specified in J2SE 1.4.2, including all optional operations. The backing data structure is a CLDC 1.1 Hashtable.

### Requirements
- **Java Development Kit (JDK)**: Required to compile and run the code.

### Compilation
```
javac -d bin -cp "JUnit/*" myAdapter/*.java myTest/*.java
```

### Running all Tests 
- **Unix**
```
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.TestRunner
```
- **Windows**
```
java -cp ";bin;JUnit/*" org.junit.runner.JUnitCore myTest.TestRunner
```

### Single Test Run 
- **Unix**
```
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterCoreTest
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterNullConstraintTest
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterEqualsHashCodeTest
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterKeySetTest
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterValuesTest
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterEntrySetTest
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterIteratorTest
```
- **Windows**
```
java -cp ";bin;JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterCoreTest
java -cp ";bin;JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterNullConstraintTest
java -cp ";bin;JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterEqualsHashCodeTest
java -cp ";bin;JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterKeySetTest
java -cp ";bin;JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterValuesTest
java -cp ";bin;JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterEntrySetTest
java -cp ";bin;JUnit/*" org.junit.runner.JUnitCore myTest.MapAdapterIteratorTest
``` 

### Documentation
- **Unix**
```
javadoc -d docs -Xdoclint:none -sourcepath . -cp ".:bin:JUnit/*" myAdapter myTest
```
- **Windows**
```
javadoc -d docs -Xdoclint:none -sourcepath . -cp ";bin;JUnit/*" myAdapter myTest
```

### Project Structure
- `myAdapter/`: Contains the `MapAdapter` implementation and related interfaces (`HMap`, `HSet`, `HCollection`, `HIterator`).
- `myTest/`: Contains the JUnit test suite ensuring full compatibility with J2SE 1.4.2 specifications and CLDC 1.1 limitations.
- `JUnit/`: Contains the required testing libraries.
