# Smart Task Manager — Powered by Claude AI

A Java desktop application with a modern dark UI that lets you manage your tasks and uses the Anthropic Claude AI to help you organize and prioritize them.

---

## Screenshots

> Add a screenshot of your app here after uploading to GitHub!

---

## Features

- Modern dark-themed JavaFX desktop interface
- Add tasks with a title, description, deadline, and priority
- Filter tasks by All, High Priority, or Completed
- Mark tasks as completed with a checkbox
- Ask Claude AI to analyze and organize your tasks
- AI suggestion panel built into the interface

---

## Project Structure

```
SmartTaskManager/
├── src/
│   ├── resources/
│   │   ├── main.fxml           # JavaFX UI layout
│   │   └── style.css           # Dark theme styling
│   ├── AiService.java          # Connects to Anthropic Claude API
│   ├── Main.java               # Original console version (kept for reference)
│   ├── MainApp.java            # JavaFX entry point
│   ├── MainController.java     # UI controller logic
│   ├── Task.java               # Task data model
│   └── TaskManager.java        # Manages the task list
├── pom.xml                     # Maven configuration and dependencies
└── README.md
```

---

## Technologies Used

- Java 21+
- JavaFX 21
- Maven
- Anthropic Java SDK (v2.17.0)
- Claude Haiku (claude-haiku-4-5) — for AI task organization

---

## Requirements

- Java 21 or higher (project uses JDK 25)
- Maven
- An Anthropic API key — get one at [console.anthropic.com](https://console.anthropic.com)

---

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/SmartTaskManager.git
cd SmartTaskManager
```

### 2. Install dependencies

```bash
mvn dependency:resolve
```

### 3. Set your API key

In IntelliJ go to **Run → Edit Configurations → MainApp → Environment Variables** and add:

```
ANTHROPIC_API_KEY=your_key_here
```

Or set it as a system environment variable on your machine.

### 4. Add VM options

In **Run → Edit Configurations → MainApp → VM Options** add:

```
--module-path "PATH_TO\.m2\repository\org\openjfx\javafx-controls\21;PATH_TO\.m2\repository\org\openjfx\javafx-fxml\21;PATH_TO\.m2\repository\org\openjfx\javafx-graphics\21;PATH_TO\.m2\repository\org\openjfx\javafx-base\21" --add-modules javafx.controls,javafx.fxml
```

Replace `PATH_TO` with your local `.m2` repository path (e.g. `C:\Users\YourName`).

### 5. Run the project

Make sure the Main class is set to `MainApp` in your Run Configuration, then hit Run!

---

## How to Use

- Click **"+ Add Task"** to create a new task with title, description, deadline and priority
- Use the sidebar to filter by **All Tasks**, **High Priority**, or **Completed**
- Check the checkbox on a task to mark it as completed
- Click **"AI Organize"** to send your tasks to Claude AI for smart suggestions

---

## Input Validation

- Task title cannot be empty
- Priority is selected from a dropdown (high / medium / low) — no invalid input possible
- Index inputs are validated against numbers only

---

## Author

Built by Angelos Chaliasis as a learning project to explore Java, JavaFX, and AI API integration.