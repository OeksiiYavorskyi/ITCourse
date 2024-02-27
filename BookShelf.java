import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BookShelf {
    private BookLinkedList books = new BookLinkedList();

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

    static class BookNode {
        Book book;
        BookNode prev;
        BookNode next;

        public BookNode(Book book) {
            this.book = book;
            this.prev = null;
            this.next = null;
        }
    }

    static class BookLinkedList implements Iterable<Book> {
        private BookNode head;
        private BookNode tail;

        public BookLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void addBook(Book book) {
            BookNode newNode = new BookNode(book);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }

        public void removeBook(String title) {
            BookNode current = head;
            while (current != null) {
                if (current.book.title.equalsIgnoreCase(title)) {
                    if (current.prev != null) {
                        current.prev.next = current.next;
                    } else {
                        head = current.next;
                    }
                    if (current.next != null) {
                        current.next.prev = current.prev;
                    } else {
                        tail = current.prev;
                    }
                    System.out.println("Книга \"" + title + "\" удалена.");
                    return;
                }
                current = current.next;
            }
            System.out.println("Книга \"" + title + "\" не найдена.");
        }

        public void displayBooks() {
            System.out.println("Список книг на полке:");
            for (Book book : this) {
                System.out.println(book);
            }
        }

        @Override
        public Iterator<Book> iterator() {
            return new Iterator<Book>() {
                private BookNode current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public Book next() {
                    Book book = current.book;
                    current = current.next;
                    return book;
                }
            };
        }
    }

    public static void main(String[] args) {
        BookShelf shelf = new BookShelf();
        Scanner scanner = new Scanner(System.in);

        // Добавляем книги для 5 разных авторов
        shelf.books.addBook(new Book("Война и мир", "Лев Толстой", 1869));
        shelf.books.addBook(new Book("Анна Каренина", "Лев Толстой", 1877));
        shelf.books.addBook(new Book("Преступление и наказание", "Федор Достоевский", 1866));
        shelf.books.addBook(new Book("Братья Карамазовы", "Федор Достоевский", 1880));
        shelf.books.addBook(new Book("1984", "Джордж Оруэлл", 1949));
        shelf.books.addBook(new Book("Ферма животных", "Джордж Оруэлл", 1945));
        shelf.books.addBook(new Book("Мастер и Маргарита", "Михаил Булгаков", 1967));
        shelf.books.addBook(new Book("Собачье сердце", "Михаил Булгаков", 1925));
        shelf.books.addBook(new Book("Унесенные ветром", "Маргарет Митчелл", 1936));
        shelf.books.addBook(new Book("Бегущий по лезвию", "Филип К. Дик", 1968));

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
                    shelf.books.addBook(new Book(title, author, year));
                    System.out.println("Книга успешно добавлена!");
                    break;
                case 2:
                    System.out.println("Введите название книги, которую хотите удалить:");
                    String titleToRemove = scanner.nextLine();
                    shelf.books.removeBook(titleToRemove);
                    break;
                case 3:
                    shelf.books.displayBooks();
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
