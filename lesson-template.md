## Lesson Overview :pencil2:

In this lesson, participants will learn the essential concepts of Continuous Integration and Continuous Deployment (CI/CD), as well as how to implement a basic CI/CD pipeline using GitHub Actions, a powerful and versatile automation tool built directly into GitHub.

GitHub Actions is an ideal choice for developers looking to automate their workflows, enabling automatic testing, building, and deployment of applications with minimal configuration. Throughout the session, you will be introduced to the importance of CI/CD in modern software development, how to set up a simple GitHub Actions workflow, and automate testing and deployment processes to streamline their projects.

<br>

## Learning Objectives :notebook:

By the end of this lesson, you will be able to:

1. Understand the concepts of Continuous Integration (CI) and Continuous Deployment (CD).
2. Learn how to set up GitHub Actions workflows.
3. Create and run a GitHub Actions workflow to automate tests and build steps.


## Key Definitions and Examples :key:

1. **Continuous Integration (CI)**:
CI is a software development practice where developers regularly merge code changes into a shared repository. Each integration is automatically verified by running tests to detect issues early.
**Example:**
Imagine a developer working on a feature branch for a Java application. Every time they push changes, a CI pipeline runs unit tests using JUnit to verify that their changes don’t break existing functionality. If the tests fail, the developer is immediately notified and can fix the issue before merging the code.
2. **Continuous Deployment (CD)**:
    CD automates the deployment of code changes to production. Once the tests pass in the CI phase, the new version is automatically deployed to production without manual intervention.

    **Example:**
    After the code passes all tests, the CD pipeline automatically deploys the new version of an app to a live environment, like a cloud server.
3. **GitHub Actions**:
    GitHub Actions is a CI/CD tool built directly into GitHub repositories. It allows you to define automated workflows using simple configuration files.

    **Example:**
    A GitHub Actions workflow file ```(.github/workflows/ci.yml)``` might run a set of unit tests using Maven and JUnit every time new code is pushed to the repository, ensuring that all changes pass the tests before they are merged.
<br>

### Detailed Steps for Creating a GitHub Actions CI/CD Pipeline:

1. **Step 1: Create a GitHub Repository** 
* Start by creating a new repository on GitHub.
* Clone the repository to your local machine, or work directly in the GitHub web interface.
2. **Step 2: Add a Workflow Configuration File** * In the root of the repository, create a `.github` directory, and within that, create a `workflows` directory.
* Inside the workflows directory, create a file named `ci.yml`.
3. **Step 3: Define a Basic CI Workflow** 
Open ci.yml and add the following content:
    ```yaml
    name: Java CI with Maven

    on:
    push:
        branches:
        - main

    jobs:
    build:
        runs-on: ubuntu-latest

        steps:
        - name: Checkout code
        uses: actions/checkout@v2

        - name: Set up JDK 11
        uses: actions/setup-java@v2
        with:
            java-version: '11'

        - name: Build with Maven
        run: mvn -B package --file pom.xml

        - name: Run tests
        run: mvn test

    ```

    **Explanation**:

    * `on: push`: This triggers the workflow when changes are pushed to the ``main`` branch.
    * `jobs: build`: A job that runs on an Ubuntu server.
    * The steps inside the job:
        1. **Checkout Code**: Downloads the code from the repository.
        2. **Set Up JDK 11**: Configures the workflow to use Java Development Kit version 11
        3. **Build with Maven**: Uses Maven to build the Java project, running the mvn package command.
        4. **Run Tests**: Runs the unit tests defined in the project using Maven’s mvn test.
    <br/>

4. **Step 4: Push Changes and See the Workflow in Action** 
    * Add a simple ``pox.xml`` file with a test script. For example:
        ```xml
        <project xmlns="http://maven.apache.org/POM/4.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
        http://maven.apache.org/xsd/maven-4.0.0.xsd">
            <modelVersion>4.0.0</modelVersion>

            <groupId>com.example</groupId>
            <artifactId>ci-demo</artifactId>
            <version>1.0-SNAPSHOT</version>

            <dependencies>
                <dependency>
                    <groupId>junit</groupId>
                    <artifactId>junit</artifactId>
                    <version>4.13.2</version>
                    <scope>test</scope>
                </dependency>
            </dependencies>

            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <version>3.8.1</version>
                        <configuration>
                            <source>11</source>
                            <target>11</target>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        </project>
    
    * Push your changes to GitHub.
    * Go to the "Actions" tab in your GitHub repository to see the CI pipeline running.

<br>

## Additional Resources :clipboard: 

If you would like to study these concepts before the class or would benefit from some remedial studying, please utilize the resources below:

- [CI/CD: The what, why, and how](https://github.com/resources/articles/devops/ci-cd)
- [YAML Syntax Documentation](https://docs.ansible.com/ansible/latest/reference_appendices/YAMLSyntax.html)
- [GitHub Training Kit](https://flask.palletsprojects.com/en/3.0.x/tutorial/)
- [GitHub Actions Documentations](https://docs.github.com/en/actions)
