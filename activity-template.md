## Introduction :pencil2:

In this activity, you will work with faulty CI pipelines using GitHub Actions. Each exercise presents a broken or misconfigured pipeline, and your job is to analyze, debug, and correct the pipeline so that it passes successfully.

**Note:** You are provided with solution to each exercise. The purpose of providing solutions to exercises is to allow you to compare your own work to see if you have a similar or correct approach.

It is not meant to be used as a way to cheat or copy the answers.

It is important to understand the reasoning behind the solution in order to improve your own understanding and problem-solving abilities.

<br>

## Setting Up a GitHub Actions Workflow for Java

### Instructions

<br>

* The CI pipeline is set up to run on a specific event, but the trigger is misconfigured. Your task is to fix the event trigger so that the pipeline runs correctly when code is **pushed** to the ``main`` branch.

**Current ``ci.yml`` File:**
```yaml
name: CI Pipeline

on:
  pull_request:
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout code
      uses: actions/checkout@v2

    - name: Build the project
      run: echo "Building the project..."

```
<br>

<details style="font-size: 14px; cursor: pointer; outline: none;">
<summary> Click for Solution </summary>

Error:

The pipeline is only configured to run on pull requests. However, the requirement is for it to run when code is pushed to the ``main`` branch. This means that regular commits to the branch are not triggering the pipeline.

Solution: 

```yaml
name: CI Pipeline

on:
  push:  # Fix: Change the event trigger to push
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout code
      uses: actions/checkout@v2

    - name: Build the project
      run: echo "Building the project..."


```

</details>

<br>

## Fixing a Misconfigured Job Matrix

### Instructions

* The pipeline is intended to test the project across multiple versions of Ubuntu, but the matrix configuration is incorrect. Your task is to fix the matrix setup so that the CI pipeline runs on both **Ubuntu 18.04** and **Ubuntu 20.04**.
* A matrix allows you to run jobs across multiple configurations, such as different operating system versions or language runtimes. You can learn more about matrix builds and how to configure them by visiting the [GitHub Actions Matrix Documentation](https://docs.github.com/en/actions/writing-workflows/choosing-what-your-workflow-does/running-variations-of-jobs-in-a-workflow).

**Current ci.yml File:**

```yaml
name: Matrix Build

on:
  push:
    branches:
      - main

jobs:
  test:
    runs-on: ubuntu-latest  # Error: No matrix is defined
    strategy:
      matrix:
        os: [ubuntu-latest]  # Error: Only one OS version is included

    steps:
    - name: Checkout code
      uses: actions/checkout@v2

    - name: Run tests
      run: echo "Running tests on matrix"
```
<br>

<details style="font-size: 14px; cursor: pointer; outline: none;">
<summary> Click for Solution </summary>

Error:

The matrix is configured to use only a single OS (``ubuntu-latest``), which defeats the purpose of using a matrix strategy. The requirement is to run the tests on both **Ubuntu 18.04** and **Ubuntu 20.04**.

Solution:

```yaml
name: Matrix Build

on:
  push:
    branches:
      - main

jobs:
  test:
    runs-on: ${{ matrix.os }}  # Fix: Use the matrix OS values
    strategy:
      matrix:
        os: [ubuntu-18.04, ubuntu-20.04]  # Fix: Add multiple OS versions

    steps:
    - name: Checkout code
      uses: actions/checkout@v2

    - name: Run tests
      run: echo "Running tests on matrix"
```
</details>
