package l33;

import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.text.SimpleDateFormat;

public class TaskManager {
    private List<Task> allTasks;
    private LinkedList<Task> taskQueue;

    public TaskManager() {
        allTasks = new ArrayList<>();
        taskQueue = new LinkedList<>();
    }

    public void addTask(Task task) {
        allTasks.add(task);
        taskQueue.add(task);
    }

    public void removeTask(int taskId) {
        Iterator<Task> iterator = allTasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == taskId) {
                iterator.remove();
                taskQueue.remove(task);
                break;
            }
        }
    }

    public void updateTaskStatus(int taskId, String newStatus) {
        boolean found = false;
        for (Task task : allTasks) {
            if (task.getId() == taskId) {
                task.setStatus(newStatus);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Задача с указанным идентификатором не найдена.");
        }
    }

    public void printAllTasks() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Task task : allTasks) {
            System.out.println(task.getId() + ": " + task.getTitle() + " - " + task.getStatus() + " (Создана: " + dateFormat.format(task.getCreatedAt()) + ")");
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Удалить задачу");
            System.out.println("3. Обновить статус задачи");
            System.out.println("4. Вывести список всех задач");
            System.out.println("5. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Введите название задачи:");
                    String title = scanner.nextLine();
                    System.out.println("Введите описание задачи:");
                    String description = scanner.nextLine();
                    Task newTask = new Task(taskManager.allTasks.size() + 1, title, description, new Date(), "новая");
                    taskManager.addTask(newTask);
                    System.out.println("Задача добавлена.");
                    break;
                case 2:
                    System.out.println("Введите идентификатор задачи для удаления:");
                    int taskIdToRemove = scanner.nextInt();
                    taskManager.removeTask(taskIdToRemove);
                    System.out.println("Задача удалена.");
                    break;
                case 3:
                    System.out.println("Введите идентификатор задачи для обновления статуса:");
                    int taskIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Введите новый статус задачи:");
                    String newStatus = scanner.nextLine();
                    taskManager.updateTaskStatus(taskIdToUpdate, newStatus);
                    break;
                case 4:
                    System.out.println("Список всех задач:");
                    taskManager.printAllTasks();
                    break;
                case 5:
                    running = false;
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
                    break;
            }
        }

        scanner.close();
    }
}
