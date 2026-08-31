# OrangeHRM_Framework
# OrangeHRM_Framework 🚀

## 📌 Project Overview

A robust and maintainable **UI Test Automation Framework** built for the OrangeHRM application using **Selenium WebDriver, Java, and TestNG**. The framework demonstrates **Page Object Model (POM), data-driven testing, dynamic test-data generation, JSON data persistence, parallel execution, retry handling, and automated reporting**.

## 🛠 Tech Stack

* **Language:** Java
* **Automation Tool:** Selenium WebDriver
* **Testing Framework:** TestNG
* **Design Pattern:** Page Object Model (POM)
* **Build Tool:** Maven
* **Test Data:** JSON + JavaFaker
* **JSON Handling:** Jackson Databind / JSON Simple
* **Excel Support:** Apache POI
* **Reporting:** ExtentReports
* **Driver Management:** WebDriverManager
* **Version Control:** Git

## 🏗 Framework Architecture

The framework is structured to support reusable and maintainable automation:

* **src/main/java:** Contains Page Objects, Abstract Components, and framework resources.
* **src/test/java:** Contains Test Classes, Test Components, Test Data, and execution configuration.
* **Page Object Model:** Separates test logic from page-specific locators and business actions.
* **Abstract Components:** Contains reusable Selenium operations and common functionality.
* **Data-Driven:** Uses JSON files with TestNG DataProviders for invalid-login scenarios.
* **Dynamic Data:** JavaFaker generates employee data during runtime.
* **Data Persistence:** Dynamically created employee details are stored in `NewUser.json`.
* **Parallel Execution:** Configured through `testng.xml` with 5 threads.
* **Reporting:** ExtentReports integrated through custom TestNG Listeners.

## ✨ Key Features

* **Page Object Model:** Modular page-level design for maintainability.
* **Data-Driven Testing:** JSON-based invalid-login data supplied through TestNG DataProviders.
* **Dynamic Test Data:** JavaFaker generates employee information during execution.
* **Randomized Employee Data:** Generates unique employee names, usernames, and employee IDs.
* **JSON Data Persistence:** Stores dynamically created employee details in `NewUser.json`.
* **Dynamic Employee Search:** Retrieves the created employee's details and searches using the generated Employee ID.
* **Synchronization:** Explicit waits implemented for reliable UI interaction.
* **Flaky Test Management:** Custom **Retry Analyzer** to re-run failed tests.
* **Parallel Execution:** TestNG configured for parallel execution using 5 threads.
* **Failure Handling:** Custom TestNG Listener handles test execution and reporting.
* **Robust Reporting:** ExtentReports provides HTML-based execution results.

## 🧪 Test Scenarios

### Login

* Valid login
* Invalid login with multiple datasets
* Error message validation
* Logout

### Employee Management

* Generate dynamic employee data
* Create employee
* Store employee details in JSON
* Retrieve the created employee
* Search employee using Employee ID
* Validate employee search results

## 🔄 Dynamic Employee Data Flow

`JavaFaker → Generate Employee Data → Create Employee → Store in NewUser.json → Retrieve Employee → Search by Employee ID → Validate Result`

## 🚀 How to Run

1. Clone the repository:
   `git clone https://github.com/Deepamattur11/OrangeHRM_Framework.git`
2. Navigate to the project folder.
3. Run tests via Maven:
   `mvn clean test`
4. Or execute the configured TestNG suite directly from the IDE.
