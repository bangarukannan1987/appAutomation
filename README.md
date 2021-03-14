# appAutomation
## Summary
Automated the test cases to execute on swaglabs app.

## Framework
Covered below cucumber+testng and testng framework. Due to the limitation on parallel execution on same feature file, i have added testng framework for parallel executions. 
Executions are controlled as profile based in maven.
### Cucumber/TestNG framework
1. TestScenarios are defined in feature files.
2. Respective step definition is available in step.definition package
3. Page Object Model design pattern used. Hence separate java files for each screen. This contains respective elements and methods.
4. DriverHandler package handles the multithread driver initilization, port selection, desired capability settings based on the test request.
5. Advanced Cucumber Reports will generate. This contains Feature level, Step level, Tag level reports.
6. Below is the maven command to initiate the run,

```mvn clean test -P cucumber-test -Dmaven.test.skip=false -Dversion=9.0 -Dplatform=android```

### TestNG framework
1. This framework will handle the same test class to execute in parallel on different devices.
2. Parameters can be configured in testng.xml
3. Extent Report is integrated to the test class.
4. Below is the maven command to initiate the run,

```mvn clean test -P testng-test -Dmaven.test.skip=false```
