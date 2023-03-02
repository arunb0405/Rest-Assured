
Pre-requisite for running tests - Start the end-point at 'http://localhost:8080/swagger-ui.html'
Java SDK 1.8 (Version 8) and Maven should be installed in the system.

# rest-assured

To run the tests in local / CI,
1.) git clone https://github.com/arunb0405/rest-assured.git 
2.) cd rest-assured
3.) Switch to dev branch
4.) mvn clean install test (from command line) OR mvn clean test -DsuiteXmlFile=testNGRunner.xml
5.) The Test NG Emailable report is generated in "rest-assured\target\surefire-reports\emailable-report.html" 

Run the tests from IntelliJ -> Right-click and run the Test NG runner file.

1.) The Test NG Runner XML file is located in Root folder, where POM.xml is present.

