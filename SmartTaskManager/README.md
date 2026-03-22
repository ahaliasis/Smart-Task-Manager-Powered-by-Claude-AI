Smart Task Manager — Powered by Claude AI
A Java console application that lets you manage your tasks and uses the Anthropic Claude AI to help you organize and prioritize them.

Features

Add tasks with a title, description, deadline, and priority
Remove tasks by index
Search for a task by index
View all tasks
Mark tasks as completed
Ask Claude AI to analyze and organize your tasks


Project Structure
SmartTaskManager/
├── src/
│   ├── Main.java          # Entry point, user input loop
│   ├── Task.java          # Task data model
│   ├── TaskManager.java   # Manages the task list
│   └── AiService.java     # Connects to the Anthropic Claude API
├── pom.xml                # Maven configuration and dependencies
└── README.md

Technologies Used

Java 21
Maven
Anthropic Java SDK (v2.17.0)
Claude Haiku (claude-haiku-4-5) — for AI task organization


Requirements

Java 21 or higher
Maven
An Anthropic API key (get one at console.anthropic.com)


Setup
1. Clone or open the project in IntelliJ IDEA
2. Load Maven dependencies
   Open pom.xml and click "Load Maven Changes" when prompted by IntelliJ.
3. Set your API key
   Go to Run → Edit Configurations → Environment Variables and add:
   ANTHROPIC_API_KEY=your_key_here
4. Run the project
   Run Main.java — the console menu will appear.

How to Use
_______________
Welcome to Task Manager Powered by AI
_________________
1 - Add task
2 - Remove task
3 - Search for task
4 - Show all tasks
5 - Mark as completed
6 - Let the AI organize your tasks
7 - Exit

Use options 1–5 to manage your tasks manually.
Use option 6 to send your task list to Claude AI, which will suggest the best order to tackle them based on priority and deadline.


Input Validation

Menu choices only accept numbers — letters will show an error.
Task priority must be high, medium, or low.
Index inputs for remove/search/complete are validated against numbers only.


Example AI Output (Option 6)
--- AI Suggestion ---
Based on your tasks, I recommend tackling "Finish homework" first
because it has a HIGH priority and is due tomorrow. After that,
"Clean room" can be done over the weekend as it is LOW priority.
---------------------

Author
Built by Angelos as a learning project to explore Java and AI API integration.