"# bumhra"

Selenium Webdriver
Cucumber
Spring
With properties file control

--Create skeleton spring boot test project

mvn archetype:generate\
 -DarchetypeGroupId=am.ik.archetype\
 -DarchetypeArtifactId=spring-boot-blank-archetype\
 -DarchetypeVersion=1.0.5

 -- Requires VM arguments
 -Dspring.profiles.active=one of mhradev, mhratest, live

 -- Example running
mvn clean test -Dtest=RunAllTest -Dcurrent.browser=hu -Dspring.profiles.active=prod -Dreport.generate=false

-- Example settings for a feature file

--plugin
org.jetbrains.plugins.cucumber.java.run.CucumberJvmSMFormatter
--plugin
json:target/cucumber.json
--monochrome
--name
"^Create an invoice processing of different types of notification$"