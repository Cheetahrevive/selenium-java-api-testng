# Selenium Java API TestNG Framework

Comprehensive Selenium WebDriver and REST API automation framework using Java, TestNG, RestAssured, Maven, and Extent Reports for robust end-to-end testing.

## Features

- **Selenium WebDriver** - Cross-browser web UI automation
- **TestNG** - Powerful test framework with annotations and parallel execution
- **RestAssured** - REST API testing and validation
- **Maven** - Dependency management and build automation
- **Page Object Model** - Maintainable test architecture
- **Extent Reports** - Beautiful HTML test reports
- **WebDriverManager** - Automatic driver management
- **Log4j** - Comprehensive logging
- **Data-Driven Testing** - Excel/CSV/JSON data providers
- **Parallel Execution** - Faster test execution
- **Screenshot Capture** - Auto-capture on test failures

## Project Structure

```
selenium-java-api-testng/
├── src/
│   ├── main/java/
│   │   ├── pages/
│   │   │   ├── BasePage.java
│   │   │   └── LoginPage.java
│   │   ├── utils/
│   │   │   ├── DriverManager.java
│   │   │   └── ExtentReportManager.java
│   │   └── api/
│   │       └── ApiClient.java
│   ├── test/java/
│   │   ├── tests/
│   │   │   ├── BaseTest.java
│   │   │   ├── LoginTest.java
│   │   │   └── ApiTest.java
│   └── test/resources/
│       ├── testng.xml
│       └── log4j2.xml
├── pom.xml
└── README.md
```

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6+
- Git

## Installation

1. Clone the repository:
```bash
git clone https://github.com/Cheetahrevive/selenium-java-api-testng.git
cd selenium-java-api-testng
```

2. Install dependencies:
```bash
mvn clean install
```

## Running Tests

### Run all tests:
```bash
mvn clean test
```

### Run specific test suite:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run tests in parallel:
```bash
mvn clean test -Dparallel=tests -DthreadCount=3
```

### Run specific test class:
```bash
mvn clean test -Dtest=LoginTest
```

### Run with specific browser:
```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
```

## Viewing Reports

After test execution:
- **Extent Reports**: `target/extent-reports/ExtentReport.html`
- **TestNG Reports**: `target/surefire-reports/index.html`

Open the HTML files in a browser to view detailed test results.

## Configuration

### TestNG XML (src/test/resources/testng.xml)
```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Test Suite" parallel="tests" thread-count="3">
    <test name="UI Tests">
        <classes>
            <class name="tests.LoginTest"/>
        </classes>
    </test>
    <test name="API Tests">
        <classes>
            <class name="tests.ApiTest"/>
        </classes>
    </test>
</suite>
```

## Key Components

### Page Object Model
```java
public class LoginPage extends BasePage {
    @FindBy(id = "username")
    WebElement usernameField;
    
    @FindBy(id = "password")
    WebElement passwordField;
    
    @FindBy(id = "login-button")
    WebElement loginButton;
    
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
```

### API Testing
```java
Response response = given()
    .header("Content-Type", "application/json")
    .body(requestBody)
.when()
    .post("/api/login")
.then()
    .statusCode(200)
    .extract().response();
```

## Best Practices

1. **Use Page Object Model** for maintainable UI tests
2. **Implement explicit waits** instead of implicit waits
3. **Use meaningful test names** and descriptions
4. **Organize tests with TestNG groups** (@Test(groups = {"smoke", "regression"}))
5. **Capture screenshots** on test failures
6. **Implement retry logic** for flaky tests
7. **Use data providers** for data-driven testing
8. **Keep tests independent** - no test dependencies
9. **Use proper logging** throughout the framework
10. **Maintain clean code** with proper comments

## CI/CD Integration

This framework is ready for CI/CD with:
- GitHub Actions
- Jenkins
- GitLab CI
- Azure DevOps

Example Jenkins Pipeline:
```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Report') {
            steps {
                publishHTML([
                    reportDir: 'target/extent-reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }
}
```

## Author

**Cheetahrevive** - Senior QA Automation Engineer
