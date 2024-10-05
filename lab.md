![logo_ironhack_blue 7](https://user-images.githubusercontent.com/23629340/40541063-a07a0a8a-601a-11e8-91b5-2f13e4e6b441.png)

# LAB | Building a CI/CD Pipeline with GitHub Actions for a Java Project


## Introduction

In this lab, you will build and configure a Continuous Integration (CI) and Continuous Deployment (CD) pipeline using GitHub Actions for a simple Java application. CI/CD pipelines automate code integration, testing, and deployment processes, ensuring that code changes are consistently tested and deployed to production in an efficient and reliable way.

You will start with a sample Java project, and your task will be to create a workflow that automatically builds, tests, and deploys the application using GitHub Actions. This lab simulates a real-world scenario where software teams integrate code frequently and rely on automated pipelines to ensure code quality and reliability.

You will be provided with the basic project setup, but most of the work in configuring the CI/CD pipeline will be up to you.

<br>

## Requirements

1. Java installed on your machine (version 11 or higher).
2. Maven installed to manage dependencies and build the project.
3. A GitHub account with a public repository to host the project.
4. Basic understanding of Java, Maven, and GitHub Actions.
5. GitHub Pages enabled for your repository.

<br>

## Instructions

Follow the guided lesson in the notebook and complete the tasks.

<br>

## Submission

Once you finish the assignment, submit a GitHub repository link on Student Portal. (**Reminder**: The link to your GitHub repository needs to be public.)

## Tasks

###  Create a Java Project
1. **Create a New Repository:**

    * Go to GitHub and create a new public repository (e.g., ``java-ci-cd-github-pages``).`
2. **Clone the Repository:**

Clone the repository to your local machine:
```bash
git clone https://github.com/YOUR_USERNAME/java-ci-cd-github-pages.git
```
3. **Create a Simple Java Application:** 
In the root directory of your repository, create the following files:

``src/main/java/com/example/App.java``:

```java
package com.example;

public class App {
    public static void main(String[] args) {
        System.out.println(getMessage());
    }

    public static String getMessage() {
        return "Hello, GitHub Pages Deployment!";
    }
}
```

``src/test/java/com/example/AppTest.java``:

```java
package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testApp() {
        // This test will always pass
        assertTrue(true);
    }

    @Test
    public void testAppMessage() {
        String expectedMessage = "Hello, GitHub Pages Deployment!";
        assertEquals(expectedMessage, App.getMessage());
    }
}
```

``pox.xml``:

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
```
This creates a basic Maven project that will be used to build, test, and deploy to GitHub Pages.


### Setting Up GitHub Actions Workflow
Your goal is to automate the build and deployment process with GitHub Actions. You will create a workflow that:

1. **Builds the Java Project** using Maven.
2. **Runs Unit Tests** to ensure the code works as expected.
3. **Deploys Static Content** (e.g., a simple HTML or documentation output) to GitHub Pages.

**Task 1: Create a Workflow File**

* In your repository, create a ``.github/workflows/`` directory.
* Inside the workflows directory, create a file called ``ci.yml``.

Basic Structure of the ``ci.yml`` File:
```yaml
name: Java CI/CD with GitHub Pages

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
           distribution: 'microsoft'
   
       - name: Build with Maven
         run: mvn -B package --file pox.xml

       - name: Generate Javadoc
         run: mvn -f pox.xml javadoc:javadoc
         
       - name: Upload Javadoc as an artifact
         uses: actions/upload-artifact@v3
         with:
           name: javadoc
           path: ./target/site/apidocs  # Upload the generated site directory      
       
       - name: Run tests
         run: mvn -f pox.xml test

  deploy:
    runs-on: ubuntu-latest
    needs: build
    steps:
       - name: Checkout code
         uses: actions/checkout@v2
         
       - name: Download Javadoc artifact
         uses: actions/download-artifact@v3
         with:
            name: javadoc  # Download the previously uploaded artifact
            path: ./javadoc
       
       - name: Deploy to GitHub Pages
         uses: peaceiris/actions-gh-pages@v4
         with:
           github_token: ${{ secrets.PERSONAL_TOKEN }}
           publish_dir: ./javadoc  # Assuming you are deploying static content from the site directory

```
**Explanation**:

* **Build Job**: This step compiles the Java project and runs unit tests using Maven.
* **Deploy Job**: After the build completes, this job deploys static content (e.g., generated documentation or HTML files) to GitHub Pages.

**Important**:
You create a new branch called ``gh-pages``.
You need to configure the GitHub Pages settings in your repository by enabling it under ``Settings > Pages``. Select the ``gh-pages`` branch as the source for GitHub Pages then save.
You [Generate a personal access token (repo)](https://github.com/settings/tokens).
Add your ``PERSONAL_TOKEN`` secret in your repository under ``Settings > Secrets and variables > Actions``.

### Generate Static Content for GitHub Pages Deployment

In this step, you will modify the Maven ``pox.xml`` to generate static site content (like Javadoc or HTML files) that can be deployed to GitHub Pages.

Add the following to your ``pox.xml`` to generate the Javadoc site:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-javadoc-plugin</artifactId>
            <version>3.3.0</version>
            <executions>
                <execution>
                    <goals>
                        <goal>javadoc</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
This configuration will generate Javadoc HTML files in the ``target/site`` directory, which will be deployed to GitHub Pages.

###  Test the GitHub Pages Deployment

#### 1. Enable GitHub Pages for the Repository
- In your GitHub repository, navigate to the repository’s **Settings**.
- Scroll down to the **"Pages"** section (under the **Code and Automation** category).
- Ensure that the **Source** is set to **"Deploy from a branch"** and that the branch is set to `gh-pages` (this is where the static content is deployed by the GitHub Actions pipeline).

#### 2. Access Your GitHub Pages Site
- Once GitHub Pages is enabled and the pipeline has run successfully, GitHub will host your static site at:

``https://<your-github-username>.github.io/<your-repository-name>/``


#### 3. Verify the Deployment
- Open the above URL in your browser.
- You should see the static content you deployed (e.g., the Javadoc site or any other HTML files). If everything is set up correctly, your static site should be live.

### 4. Verify GitHub Actions Workflow Logs
- Go to the **"Actions"** tab in your repository.
- Look for the most recent workflow run (it should be triggered by your latest push to the `main` branch).
- Open the workflow details and check if all steps were executed successfully (no red errors). Specifically, confirm that the build, tests, and deployment steps all completed successfully.
- You can see if the **Deploy to GitHub Pages** step was successful by viewing the logs in the **Actions** tab.

#### 5. Check GitHub Pages Build Status
- After deployment, GitHub Pages will build your site. You can check the build status by going to the **Settings > Pages** section.
- If there were any errors, GitHub will display them in the **GitHub Pages** section.

#### 6. Re-run the Pipeline (if needed)
- If you notice any issues (e.g., the page doesn't load or the static content isn’t showing as expected), review your workflow configuration (`ci.yml`), static content generation (e.g., Javadoc generation), and redeploy the content by making a new commit or manually triggering the workflow.

# Troubleshooting

### 1. Page Not Found (404 Error)
- Ensure that the GitHub Pages is enabled correctly and that your deployment workflow pushed content to the `gh-pages` branch.
- Double-check the URL format: `https://<your-github-username>.github.io/<your-repository-name>/`.

### 2. Broken Links or Missing Content
- Verify that the content (HTML, Javadoc, or other static files) is correctly generated in the `target/site` folder and pushed to the `gh-pages` branch.
- Check the GitHub Pages site for missing or broken links by inspecting the generated HTML.

### 3. Build Errors in GitHub Actions
- If the GitHub Actions workflow is failing, check the logs in the **Actions** tab to identify which step failed. This could be due to Maven build failures, failed tests, or an incorrect GitHub Pages deployment configuration.
