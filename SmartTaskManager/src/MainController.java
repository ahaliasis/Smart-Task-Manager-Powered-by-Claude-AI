import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.CheckBox;
import javafx.geometry.Pos;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {

    @FXML private VBox taskListContainer;
    @FXML private Label aiSuggestion;
    @FXML private Label toolbarTitle;

    private TaskManager taskManager = new TaskManager();
    private AiService aiService = new AiService();

    @FXML
    public void initialize() {
        refreshTaskList(taskManager.getTasks());
    }

    @FXML
    private void openAddTask() {
        javafx.scene.control.Dialog<Task> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("Add Task");
        dialog.setHeaderText("New Task");

        javafx.scene.control.ButtonType addButton =
                new javafx.scene.control.ButtonType("Add", javafx.scene.control.ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, javafx.scene.control.ButtonType.CANCEL);

        javafx.scene.control.TextField titleField = new javafx.scene.control.TextField();
        titleField.setPromptText("Task title");
        javafx.scene.control.TextField descField = new javafx.scene.control.TextField();
        descField.setPromptText("Description");
        javafx.scene.control.TextField deadlineField = new javafx.scene.control.TextField();
        deadlineField.setPromptText("Deadline (e.g. Tomorrow)");
        javafx.scene.control.ComboBox<String> priorityBox = new javafx.scene.control.ComboBox<>();
        priorityBox.getItems().addAll("high", "medium", "low");
        priorityBox.setValue("medium");

        VBox form = new VBox(8, new Label("Title:"), titleField,
                new Label("Description:"), descField,
                new Label("Deadline:"), deadlineField,
                new Label("Priority:"), priorityBox);
        form.setPadding(new javafx.geometry.Insets(10));
        dialog.getDialogPane().setContent(form);

        dialog.setResultConverter(btn -> {
            if (btn == addButton) {
                String title = titleField.getText().trim();
                if (!title.isBlank()) {
                    return new Task(title, descField.getText(),
                            deadlineField.getText(), priorityBox.getValue(), false);
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(task -> {
            taskManager.addTask(task);
            refreshTaskList(taskManager.getTasks());
        });
    }

    @FXML
    private void showAllTasks() {
        toolbarTitle.setText("All Tasks");
        refreshTaskList(taskManager.getTasks());
    }

    @FXML
    private void showHighPriority() {
        toolbarTitle.setText("High Priority");
        List<Task> filtered = taskManager.getTasks().stream()
                .filter(t -> t.getPriority().equalsIgnoreCase("high"))
                .collect(Collectors.toList());
        refreshTaskList(filtered);
    }

    @FXML
    private void showCompleted() {
        toolbarTitle.setText("Completed");
        List<Task> filtered = taskManager.getTasks().stream()
                .filter(Task::isCompleted)
                .collect(Collectors.toList());
        refreshTaskList(filtered);
    }

    @FXML
    private void askAI() {
        if (taskManager.getTasks().isEmpty()) {
            aiSuggestion.setText("No tasks yet! Add some tasks first.");
            return;
        }
        aiSuggestion.setText("Thinking...");
        new Thread(() -> {
            String result = aiService.organizeTasks(taskManager.getTasks());
            javafx.application.Platform.runLater(() -> aiSuggestion.setText(result));
        }).start();
    }

    private void refreshTaskList(List<Task> tasks) {
        taskListContainer.getChildren().clear();
        if (tasks.isEmpty()) {
            Label empty = new Label("No tasks here yet!");
            empty.setStyle("-fx-text-fill: #666666; -fx-font-size: 13px;");
            taskListContainer.getChildren().add(empty);
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            taskListContainer.getChildren().add(buildTaskCard(tasks.get(i), i));
        }
    }

    private HBox buildTaskCard(Task task, int index) {
        HBox card = new HBox(12);
        card.getStyleClass().add("task-card");
        card.setAlignment(Pos.CENTER_LEFT);

        CheckBox check = new CheckBox();
        check.setSelected(task.isCompleted());
        check.setOnAction(e -> {
            task.setCompleted(check.isSelected());
            refreshTaskList(taskManager.getTasks());
        });

        VBox info = new VBox(3);
        HBox.setHgrow(info, javafx.scene.layout.Priority.ALWAYS);
        Label title = new Label(task.getTitle());
        title.getStyleClass().add(task.isCompleted() ? "task-title-done" : "task-title");
        Label meta = new Label("Due: " + task.getDeadline() + " · " + task.getDescription());
        meta.getStyleClass().add("task-meta");
        info.getChildren().addAll(title, meta);

        Label badge = new Label(task.getPriority().toUpperCase());
        badge.getStyleClass().add("badge-" + task.getPriority().toLowerCase());

        card.getChildren().addAll(check, info, badge);
        return card;
    }
}