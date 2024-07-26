package com.bibliotheque;

import java.io.IOException;

import com.bibliotheque.classes.Biography;
import com.bibliotheque.classes.Book;
import com.bibliotheque.classes.LibraryManager;
import com.bibliotheque.classes.Novel;
import com.bibliotheque.classes.ScienceFiction;

public class Main {
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();

        // Add books
        library.addBook(new Novel("1", "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new ScienceFiction("2", "Dune", "Frank Herbert"));
        library.addBook(new Biography("3", "Steve Jobs", "Walter Isaacson"));

        // Save and load
        try {
            library.saveToFile("library.txt");
            library.loadFromFile("library.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Example usage
        System.out.println("Book count: " + library.getBookCount());
        System.out.println("Books starting with 'T': " + library.listBooksByLetter('T'));
        System.out.println("Books in Novel category: " + library.getBooksByCategory("Novel"));

        // Exception handling
        try {
            Book book = library.getBookByIdWithException("1");
            System.out.println("Found book: " + book);
        } catch (LibraryManager.BookNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
