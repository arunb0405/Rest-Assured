
Pre-requisite before running tests - Start the end-point at 'http://localhost:8080/swagger-ui.html'

# rest-assured
1.) The Test NG Runner XML file is located in Root folder.
2.) The API accepts a MaxTestVal parameter for which the end-point gets called.
This parameter limits the count in response body.
If (MaxTestVal > response parameter count) it returns MaxTestVal. 
e.g if 50 is supplied as value, the max repetition of words is 45 or 46, and the complete response up till this value is validated.
3.) If (MaxTestVal < response parameter count), e.g 5 < 45, the API gets called 5 times and response is validated up till this.
4.) There are negative validation tests for 401 and 500 which includes schema validations.

To run the tests in local / CI,
a.) git clone https://github.com/arunb0405/rest-assured-bdd.git 
b.) cd rest-assured-bdd
c.) Configure 'MaxTestVal' in testngRunner.xml before running test and change the content text to any desired values
d.) mvn clean install test (from command line)
e.) The Test NG Emailable report is generated in "rest-assured-bdd\target\surefire-reports\emailable-report.html" 
