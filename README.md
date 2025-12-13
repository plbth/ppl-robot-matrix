# Robot Control DSL Interpreter

A Project for Principles of Programming Languages (PPL) Course.
This application implements an interpreter for a Domain Specific Language (DSL) that controls a robot on a 10x10 grid matrix using Java, ANTLR 4, and JavaFX.

## Project Overview

* **Language:** Java 17
* **Parser Generator:** ANTLR 4.9.2
* **GUI Framework:** JavaFX
* **Build Tool:** Maven (Preferred) or Manual Classpath

## Features

* **Grid System:** 10x10 matrix visualization.
* **Control Flow:** Supports nested `LOOP` statements.
* **Interpreter:** Uses Visitor pattern to process AST and manage Robot State (Immutable).
* **Safety:** Boundary checks ensure the robot stays within the [0,9] grid index.
* **Animation:** Step-by-step movement visualization.

## DSL Syntax Example

```text
BEGIN
    SET_PEN "red"
    MOVE FORWARD 3
    TURN RIGHT
    LOOP 4 {
        MOVE FORWARD 2
        TURN RIGHT
    }
END
```

## Directory Structure

```
PPL_Project/
├── lib/                  # External JARs (antlr-4.9.2-complete.jar) - Only if not using Maven
├── src/
│   └── main/
│       ├── java/         # Source code (Model, Logic, UI)
│       └── resources/    # Grammar (.g4), FXML layout, CSS
├── pom.xml               # Maven configuration
└── README.md
```

## How to Run

You can build and run this project using either Maven (Recommended) or Manually via command line.

### Option 1: Using Maven (Recommended)

Ensure you have Maven installed and added to your PATH.

1. **Generate ANTLR Code:**
   The project is configured to use the local jar for generation if necessary, but Maven usually handles dependencies. If using the provided manual generation command:

   ```bash
   java -jar lib/antlr-4.9.2-complete.jar -no-listener -visitor -package com.group.antlr -o src/main/java/com/group/antlr src/main/resources/Robot.g4
   ```
2. **Clean and Compile:**

   ```bash
   mvn clean compile
   ```
3. **Run the Application:**

   ```bash
   mvn javafx:run
   ```

### Option 2: Manual Compilation (No Maven)

If you cannot use Maven, follow these specific commands to compile and run using the provided `lib` folder.

**Prerequisites:** Java JDK 17+ installed.

1. **Generate Parser Sources:**
   Execute this command from the project root:

   ```bash
   java -jar lib/antlr-4.9.2-complete.jar -no-listener -visitor -package com.group.antlr -o src/main/java/com/group/antlr src/main/resources/Robot.g4
   ```
2. **Compile Source Code:**
   Note: Windows users use `;` for classpath separator. Linux/Mac users use `:`.

   *Windows:*

   ```powershell
   mkdir bin
   javac -cp "src/main/java;lib/antlr-4.9.2-complete.jar" -d bin src/main/java/com/group/antlr/*.java src/main/java/com/group/model/*.java src/main/java/com/group/logic/*.java src/main/java/com/group/*.java
   ```
3. **Run the Backend Test (Console):**

   ```powershell
   java -cp "bin;lib/antlr-4.9.2-complete.jar" com.group.BackendTest
   ```

*Note: Running the JavaFX UI manually without Maven/Gradle requires setting up JavaFX modules in the command line argument, which is complex. Using Maven (Option 1) is strongly advised for the UI.*

## Development Workflow (Only recommend)

1.  **Fork** this repository first, then clone your fork.
2.  Create a feature branch (e.g., `git checkout -b feature/ui-design`).
3.  Commit your changes.
4.  Push to your forked repository.
5.  Create a **Pull Request** to the main repository.

## Architecture Notes

*   **Backend:** Located in `com.group.logic` and `com.group.model`. The `RobotState` is immutable.
*   **Frontend:** Located in `com.group.ui`. Uses `Canvas` for rendering the grid and robot.
*   **Coordinates:** The system uses a matrix coordinate system where (0,0) is the top-left cell.
