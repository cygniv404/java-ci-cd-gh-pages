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

## Solution

Follow [this link](https://github.com/cygniv404/java-ci-cd-gh-pages) to get to the repository with a working solution.

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
   
   * Java Class ``src/main/java/com/example/App.java``:
      - Have a main method that prints a message to the console.
      - Have a static method that returns a string message, which will later be used for testing.
      - For example, think of a method that returns a greeting message like “Hello, GitHub Pages       Deployment!”.
    
   * Test Class using **JUnit** ``src/test/java/com/example/AppTest.java``:
      Write two test methods:
        - One that always passes (a basic sanity check).
        - Another that checks if the message returned by your method is correct.
   * Set up Maven ``pom.xml``:
      - In the root of your repository, create a Maven POM file (pom.xml). This file manages dependencies and plugins for your project. Include          
        dependencies for JUnit and Maven compiler to compile Java code and run tests.
      - Configure Maven to use Java version 11.

### Setting Up GitHub Actions Workflow
   Your goal is to automate the build and deployment process with GitHub Actions. You will create a workflow that:
   
   1. **Builds the Java Project** using Maven.
   2. **Runs Unit Tests** to ensure the code works as expected.
   3. **Deploys Static Content** (e.g., a simple HTML or documentation output) to GitHub Pages.

#### **Create a Workflow File:**

   * In your repository, create a ``.github/workflows/`` directory.
   * Inside the workflows directory, create a file called ``ci.yml``.

**Explanation**:

   * **Build Job**: This step compiles the Java project, crates static files and runs unit tests using Maven.
   * **Deploy Job**: After the build completes, this job deploys static content (from the Build job) to GitHub Pages.

**Important**:

   You need to create a new branch called ``gh-pages``.
   You need to configure the GitHub Pages settings in your repository by enabling it under ``Settings > Pages``. Select the ``gh-pages`` branch as the source for GitHub Pages then save.
   You [Generate a personal access token (repo)](https://github.com/settings/tokens).
   Add your ``PERSONAL_TOKEN`` secret in your repository under ``Settings > Secrets and variables > Actions``.

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
