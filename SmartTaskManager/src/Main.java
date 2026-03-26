import java.util.Scanner;
import java.util.InputMismatchException;
public class Main{
    public static void main(String[] args) {


        TaskManager manager = new TaskManager();
        AiService aiService = new AiService();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean valid = false;
        boolean isRunning = true;
        int choice1 = 0;
        boolean valid1 = false;
        int choice2 = 0;
        boolean valid2 = false;
        String priority;
        boolean validPriority = false;

        while(isRunning){

            System.out.println("_______________");
            System.out.println("Welcome to Task Manager Powered by AI");
            System.out.println("_________________");
            System.out.println("1-Add task");
            System.out.println("2-Remove task");
            System.out.println("3-Search For Task");
            System.out.println("4-Show all tasks");
            System.out.println("5-Marked as completed");
            System.out.println("6-Let the AI organize your tasks");
            System.out.println("7-Exit");


            do{
                try{
                    System.out.print("Choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    valid = true;


                }catch (InputMismatchException e){
                    System.out.println("Only Numbers");
                    scanner.nextLine();
                    valid = false;

                }

            }while(!valid);

            switch(choice){
                case 1:
                    System.out.println("Name Task: ");
                    String name= scanner.nextLine();

                    System.out.println("Description of " + name);
                    String description = scanner.nextLine();

                    System.out.println("Deadline :");
                    String deadline = scanner.nextLine();

                    do {
                        System.out.println("Set priority for " + name + " (high / medium / low): ");
                        priority = scanner.nextLine().toLowerCase();
                        if (priority.equals("high") || priority.equals("medium") || priority.equals("low")) {
                            validPriority = true;
                        } else {
                            System.out.println("Invalid priority. Please enter high, medium or low.");
                        }
                    } while (!validPriority);

                    Task t = new Task(name,description,deadline,priority,false);
                    manager.addTask(t);

                    System.out.println("Task added");
                    break;

                case 2:

                    do {
                        try {


                            System.out.println("Give index: ");
                            int idx = scanner.nextInt();
                            scanner.nextLine();
                            manager.removeTask(idx);
                            valid1 = true;
                        }catch(InputMismatchException e){
                            System.out.println("Only numbers");
                            scanner.nextLine();
                            valid1 = false;
                        }

                    }while(!valid1);

                case 3:
                    System.out.println("Give index");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    Task found = manager.getTask(index);
                    if(found != null){
                        System.out.println(found);
                    }else{
                        System.out.println("Task not found");
                    }

                    break;
                case 4:
                    manager.showAllTasks();
                    break;

                case 5:

                    do {

                        try {
                            System.out.println("Give index: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            Task done = manager.getTask(id);


                            if (done != null) {
                                done.setCompleted(true);
                                System.out.println("Task completed");
                            }
                            valid2 = true;
                        }catch (InputMismatchException e){
                            System.out.println("Only numbers");
                            scanner.nextLine();
                            valid2 = false;
                        }
                    }while(!valid2);

                    break;
                case 6:
                    System.out.println("Asking AI to organize your tasks...");
                    String suggestion = aiService.organizeTasks(manager.getTasks());
                    System.out.println("\n--- AI Suggestion ---");
                    System.out.println(suggestion);
                    System.out.println("---------------------");
                    break;

                case 7:
                    isRunning = false;
                    System.out.println("Exiting");
                    break;

                default:
                    System.out.println("Enter a valid choice");
                    break;

            }

        }



    }}

