# ASCII Draw

### Run
ASCII Draw requires Java 1.8+ to run.
To run ASCII, please execute below command.

### Build
Here is the build procedure for Linux
##### Direct build and execute
```sh
$ ./run.sh
```
By executing above command, gradle wrapper would download gradle and build a jar. After that, java would run the jar and user can enter the commands.
##### Build alone
```sh
$ ./gradlew clean fatJar
```
Gradle would do a clean and build a fatJar under build/libs under current directory

###### Run after build
```
java -jar build/libs/build/libs/com.ubs.takehome-all-1.0.jar
```
Executing the program

### Test
```sh
$./gradlew clean test
```
### Log
log is named as debug.log in the executing directory


### Development
Developer can implement their own reader for parsing different commands. Implementing different strategy can draw in a different way.