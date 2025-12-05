
class Reader {
    public void register() {
        System.out.println("Регистрация пользователя");
    }

    public void viewBooks() {
        System.out.println("Просмотр доступных книг");
    }

    public void searchBooks(String query) {
        System.out.println("Поиск книг по: " + query);
    }

    public void bookBook(String book) {
        System.out.println("Бронирование книги: " + book);
    }

    public void cancelBooking(String book) {
        System.out.println("Отмена бронирования книги: " + book);
    }

    public void viewHistory() {
        System.out.println("Просмотр истории бронирований");
    }
}


class Librarian extends Reader {
    public void addBook(String book) {
        System.out.println("Добавление книги: " + book);
    }

    public void removeBook(String book) {
        System.out.println("Удаление книги: " + book);
    }

    public void issueBook(String book, String reader) {
        System.out.println("Выдача книги '" + book + "' читателю " + reader);
    }

    public void returnBook(String book, String reader) {
        System.out.println("Возврат книги '" + book + "' от читателя " + reader);
    }

    public void viewActiveBookings() {
        System.out.println("Просмотр списка активных бронирований");
    }

    public void manageCatalog() {
        System.out.println("Управление каталогом книг");
    }
}


class Admin extends Librarian {
    public void manageBranches() {
        System.out.println("Управление филиалами библиотеки");
    }

    public void manageAccounts() {
        System.out.println("Управление учетными записями пользователей и библиотекарей");
    }

    public void viewAnalytics() {
        System.out.println("Просмотр аналитики (выданные книги, популярные книги и т.д.)");
    }
}


public class usecaseLibrarySystem {
    public static void main(String[] args) {
        Reader reader = new Reader();
        reader.register();
        reader.viewBooks();

        Librarian librarian = new Librarian();
        librarian.addBook("Java для начинающих");
        librarian.issueBook("Java для начинающих", "Мөлдір");

        Admin admin = new Admin();
        admin.manageBranches();
        admin.viewAnalytics();
    }
}
