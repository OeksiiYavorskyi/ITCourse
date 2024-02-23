import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BookShelf {
    private List<Book> books = new ArrayList<>();

    static class Book {
        String title;
        String author;
        int year;

        Book(String title, String author, int year) {
            this.title = title;
            this.author = author;
            this.year = year;
        }

        @Override
        public String toString() {
            return title + " by " + author + ", " + year;
        }
    }

    void addBook(String title, String author, int year) {
        books.add(new Book(title, author, year));
    }

    void removeBook(String title) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.title.equalsIgnoreCase(title)) {
                iterator.remove();
                System.out.println("Книга \"" + title + "\" удалена.");
                return;
            }
        }
        System.out.println("Книга \"" + title + "\" не найдена.");
    }

    void displayBooks() {
        System.out.println("Список книг на полке:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        BookShelf shelf = new BookShelf();
        Scanner scanner = new Scanner(System.in);

        // Добавляем книги для 5 разных авторов
        shelf.addBook("Война и мир", "Лев Толстой", 1869);
        shelf.addBook("Анна Каренина", "Лев Толстой", 1877);
        shelf.addBook("Преступление и наказание", "Федор Достоевский", 1866);
        shelf.addBook("Братья Карамазовы", "Федор Достоевский", 1880);
        shelf.addBook("1984", "Джордж Оруэлл", 1949);
        shelf.addBook("Ферма животных", "Джордж Оруэлл", 1945);
        shelf.addBook("Мастер и Маргарита", "Михаил Булгаков", 1967);
        shelf.addBook("Собачье сердце", "Михаил Булгаков", 1925);
        shelf.addBook("Унесенные ветром", "Маргарет Митчелл", 1936);
        shelf.addBook("Бегущий по лезвию", "Филип К. Дик", 1968);

        System.out.println("Добро пожаловать в виртуальную книжную полку!");
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Удалить книгу");
            System.out.println("3. Показать список книг");
            System.out.println("4. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Чтобы считать символ новой строки после считывания числа

            switch (choice) {
                case 1:
                    System.out.println("Введите название книги:");
                    String title = scanner.nextLine();
                    System.out.println("Введите автора книги:");
                    String author = scanner.nextLine();
                    System.out.println("Введите год издания книги:");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Чтобы считать символ новой строки после считывания числа
                    shelf.addBook(title, author, year);
                    System.out.println("Книга успешно добавлена!");
                    break;
                case 2:
                    System.out.println("Введите название книги, которую хотите удалить:");
                    String titleToRemove = scanner.nextLine();
                    shelf.removeBook(titleToRemove);
                    break;
                case 3:
                    shelf.displayBooks();
                    break;
                case 4:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }
}
