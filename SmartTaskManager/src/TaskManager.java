
import java.util.ArrayList;

public class TaskManager {


   private ArrayList <Task> tasks;

   public TaskManager() {
        tasks = new ArrayList<>();
    }



    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index); //thelei elexo
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void showAllTasks(){
       if(tasks.isEmpty()){
           System.out.println("No tasks available");
           return;
       }
       for(int i = 0; i < tasks.size(); i++){
           System.out.println("Task #" + i);
           System.out.println(tasks.get(i));
       }
    }
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
