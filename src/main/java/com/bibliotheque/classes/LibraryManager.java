package com.bibliotheque.classes;

import java.util.*;
import java.io.*;

public class LibraryManager {
    private HashMap<String, Book> books;

    public LibraryManager() {
        books = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void removeBook(String id) {
        books.remove(id);
    }

    public void updateBook(String id, String title, String author) {
        Book book = books.get(id);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
        }
    }

    public Book searchBookByName(String name) {
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(name)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> listBooksByLetter(char letter) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().charAt(0) == letter) {
                result.add(book);
            }
        }
        return result;
    }

    public int getBookCount() {
        return books.size();
    }

    public List<Book> getBooksByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }

    public Book getBookById(String id) {
        return books.get(id);
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Book book : books.values()) {
                writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getCategory());
                writer.newLine();
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    String category = parts[3];
                    Book book = null;
                    switch (category) {
                        case "Novel":
                            book = new Novel(id, title, author);
                            break;
                        case "Science Fiction":
                            book = new ScienceFiction(id, title, author);
                            break;
                        case "Biography":
                            book = new Biography(id, title, author);
                            break;
                    }
                    if (book != null) {
                        addBook(book);
                    }
                }
            }
        }
    }

    // Custom Exception
    public static class BookNotFoundException extends Exception {
        public BookNotFoundException(String message) {
            super(message);
        }
    }

    public Book getBookByIdWithException(String id) throws BookNotFoundException {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        return book;
    }
}
