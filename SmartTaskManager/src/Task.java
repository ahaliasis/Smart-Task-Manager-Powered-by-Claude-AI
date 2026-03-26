public class Task {
    private String title;
    private String description;
    private String deadline;
    private String priority;
    private boolean completed;


    public Task(String title,String description,String deadline,String priority,boolean completed){
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }
    public String getPriority(){
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                "\nDescription: " + description +
                "\nDeadline: " + deadline +
                "\nPriority: " + priority +
                "\nCompleted: " + (completed ? "Yes" : "No") +
                "\n-----------------------------";
    }


}
