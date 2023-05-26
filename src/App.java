import java.util.Scanner;

public class App {

    private static DoublyLinkedList taskList;
    private static MinHeap taskHeap;

    public static void main(String[] args) throws Exception {
        taskList = new DoublyLinkedList();
        taskHeap = new MinHeap(100);
        showMenu();
    }

    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----- To-Do List -----");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Remove Task");
            System.out.println("4. Display Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    markTaskAsCompleted(scanner);
                    break;
                case 3:
                    removeTask(scanner);
                    break;
                case 4:
                    displayTasks();
                    break;
                case 5:
                    System.out.println("Exiting the application...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task priority: ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int taskId = taskList.getSize() + 1; // Assign a unique ID based on task list size
        Task newTask = new Task(taskId, priority, description);
        taskList.addTask(newTask);
        taskHeap.addTask(newTask);
        System.out.println("Task added successfully.");
    }

    private static void markTaskAsCompleted(Scanner scanner) {
        System.out.print("Enter task ID to mark as completed: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Search and mark task as completed in both data structures
        markTaskAsCompletedInList(taskId);
        markTaskAsCompletedInHeap(taskId);
    }

    private static void markTaskAsCompletedInList(int taskId) {
        // Mark task as completed in the doubly linked list
        Node current = taskList.getHead();
        while (current != null) {
            if (current.getTask().getId() == taskId) {
                current.getTask().setCompleted(true);
                System.out.println("Task marked as completed in the list.");
                return;
            }
            current = current.getNext();
        }
        System.out.println("Task not found in the list.");
    }

    private static void markTaskAsCompletedInHeap(int taskId) {
        // Mark task as completed in the min-heap
        for (int i = 0; i < taskHeap.getSize(); i++) {
            if (taskHeap.getHeapArray()[i].getId() == taskId) {
                taskHeap.getHeapArray()[i].setCompleted(true);
                System.out.println("Task marked as completed in the heap.");
                return;
            }
        }
        System.out.println("Task not found in the heap.");
    }

    private static void removeTask(Scanner scanner) {
        System.out.print("Enter task ID to remove: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Remove task from both data structures
        removeTaskFromList(taskId);
        removeTaskFromHeap(taskId);
    }

    private static void removeTaskFromList(int taskId) {
        taskList.removeTask(taskId);
        System.out.println("Task removed from the list.");
    }

    private static void removeTaskFromHeap(int taskId) {
        Task removedTask = taskHeap.removeTask(taskId);
        if (removedTask != null) {
            System.out.println("Task removed from the heap.");
        } else {
            System.out.println("Task not found in the heap.");
        }
    }

    private static void displayTasks() {
        System.out.println("----- Task List -----");
        System.out.println("List:");
        taskList.displayTasks();
        System.out.println("Heap:");
        taskHeap.displayTasks();
        System.out.println("---------------------");
    }
}
