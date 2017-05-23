package edu.hm.cs.swa.controller;

import edu.hm.cs.swa.model.Book;
import edu.hm.cs.swa.model.Disc;
import edu.hm.cs.swa.model.Medium;
import edu.hm.cs.swa.model.User;
import org.eclipse.jetty.server.Authentication;

/**
 * Created by CaptainEinsicht on 03.05.2017.
 * Alle Rechte vorbehalten bei Missbrauch wird zurück Missbraucht
 */
public interface MediaService {

    /**
     * Method for adding a book to library.
     *
     * @param newBook book that will be added.
     * @return status code whether successful or not.
     */
    MediaServiceResult addBook(Book newBook);

    /**
     * Method for adding a disc to library.
     *
     * @param newDisc disc that will be added.
     * @return status code whether successful or not.
     */
    MediaServiceResult addDisc(Disc newDisc);

    /**
     * Get the list of books.
     *
     * @return An array of books available.
     */
    Book[] getBooks();

    /**
     * Get the list of discs.
     *
     * @return An array of discs available.
     */
    Medium[] getDiscs();

    MediaServiceResult login(User user);

    /**
     * Get a specific book, identified by the isbn.
     *
     * @param isbn isbn of the desired book.
     * @return status code indicating success or failure.
     */
    Medium getBook(String isbn);

    /**
     * Get a specific disc, identified by the barcode.
     *
     * @param barcode barcode of the desired disc.
     * @return status code indicating success or failure.
     */
    Medium getDisc(String barcode);

    /**
     * Update an existing book.
     *
     * @param oldBook Book that needs to be updated.
     * @return status code indicating success or failure.
     */
    MediaServiceResult updateBook(Book oldBook);

    /**
     * Update an existing disc.
     *
     * @param oldDisc Disc that needs to be updated.
     * @return status code indicating success or failure.
     */
    MediaServiceResult updateDisc(Disc oldDisc);

    /**
     * login a user
     *
     * @param user who wants to log in
     * @return some status stuff
     */
    MediaServiceResult login(Authentication.User user);

}
