package edu.hm.cs.swa.controller;

import edu.hm.cs.swa.model.*;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Implementation of the MediaService.
 *
 * @author Johannes Seidel, Michael Reile.
 */
public class MediaServiceImpl implements MediaService {

    public static final int THREE = 3;

    public static final int TEN = 10;

    public static final int TWELVE = 12;

    public static final int THIRTEEN = 13;

    private HashMap<String, Book> bookHashMap = new HashMap<>();

    private HashMap<String, Disc> discHashMap = new HashMap<>();

    private HashSet<User> userHashMap = new HashSet<>();

    private String isbn;


    /**
     * Default c'tor.
     */
    public MediaServiceImpl() {
        User kevin = new User("Chicksterminator", "Kevin", "penopt", "20quicksniperlord05", TWELVE);
        userHashMap.add(kevin);
    }


    @Override
    public MediaServiceResult addBook(Book newBook) {
        MediaServiceResult msr = MediaServiceResult.OK;

        if (!validISBN(newBook.getIsbn())) {
            msr = MediaServiceResult.INVALID_ISBN;
        } else if (newBook.getIsbn().equals("")) {
            msr = MediaServiceResult.ISBN_NOT_FOUND;
        } else if (bookHashMap.containsKey(newBook.getIsbn())) {
            msr = MediaServiceResult.ISBN_TAKEN;
        } else if (newBook.getAuthor().equals("") || newBook.getTitle().equals("")) {
            msr = MediaServiceResult.AUTHOR_OR_TITLE_MISSING;
        } else {
            bookHashMap.put(newBook.getIsbn(), newBook);
        }

        return msr;
    }


    @Override
    public MediaServiceResult addDisc(Disc newDisc) {
        MediaServiceResult msr = MediaServiceResult.OK;

        if (newDisc.getBarcode().equals("")) {
            msr = MediaServiceResult.ISBN_NOT_FOUND;
        } else if (discHashMap.containsKey(newDisc.getBarcode())) {
            msr = MediaServiceResult.ISBN_TAKEN;
        } else if (newDisc.getDirector().equals("") || newDisc.getTitle().equals("")) {
            msr = MediaServiceResult.AUTHOR_OR_TITLE_MISSING;
        } else {
            discHashMap.put(newDisc.getBarcode(), newDisc);
        }

        return msr;
    }


    @Override
    public Book[] getBooks() {
        Book[] allBooks = new Book[bookHashMap.values().size()];
        int counter = 0;
        for (Book book : bookHashMap.values()) {
            allBooks[counter] = book;
            counter++;
        }
        return allBooks;
        //return bookHashMap.values().toArray(new Book[bookHashMap.size()]);
    }


    @Override
    public Disc[] getDiscs() {

        Disc[] allDiscs = new Disc[discHashMap.size()];
        int counter = 0;
        for (Disc disc : discHashMap.values()) {
            allDiscs[counter] = disc;
            counter++;
        }
        return allDiscs;

    }


    @Override
    public MediaServiceResult updateBook(Book newBook) {
        final MediaServiceResult result;
        final Book bookToChange = bookHashMap.get(newBook.getIsbn());

        if (bookToChange == null) {
            result = MediaServiceResult.ISBN_NOT_FOUND;
        } else if (newBook.getAuthor() == null || newBook.getTitle() == null) {
            result = MediaServiceResult.AUTHOR_OR_TITLE_MISSING;
        } else {
            bookHashMap.replace(newBook.getIsbn(), newBook);
            result = MediaServiceResult.OK;
        }

        return result;
    }


    @Override
    public MediaServiceResult updateDisc(Disc newDisc) {
        final MediaServiceResult result;
        final Disc discToChange = discHashMap.get(newDisc.getBarcode());

        if (discToChange == null) {
            result = MediaServiceResult.ISBN_NOT_FOUND;
        } else if (newDisc.getDirector() == null || newDisc.getTitle() == null) {
            result = MediaServiceResult.AUTHOR_OR_TITLE_MISSING;
        } else {
            discHashMap.replace(newDisc.getBarcode(), newDisc);
            result = MediaServiceResult.OK;
        }

        return result;
    }


    @Override
    public MediaServiceResult login(final User user) {
        final MediaServiceResult result;

        if (user == null) {
            result = MediaServiceResult.AUTHORIZATION;
        } else if (!userHashMap.contains(user)) {
            result = MediaServiceResult.AUTHORIZATION;
        } else if (user.getUserToken() != null) {
            result = MediaServiceResult.OK;
        } else {
            user.setUserToken(generateToken(user));
            result = MediaServiceResult.OK;
        }
        return result;
    }


    @Override
    public Medium getBook(String isbn) {
        return bookHashMap.get(isbn);
    }


    @Override
    public Medium getDisc(String barcode) {
        return discHashMap.get(barcode);
    }


    /**
     * Validate isbn.
     *
     * @param isbn Isbn number.
     * @return true if valid, else false.
     */
    private boolean validISBN(String isbn) {
        if (isbn == null) {
            return false;
        }

        isbn = isbn.replaceAll("-", "");

        if (isbn.length() != THIRTEEN) {
            return false;
        }

        try {
            int tot = 0;
            for (int i = 0; i < TWELVE; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                tot += (i % 2 == 0) ? digit : digit * THREE;
            }

            //checksum must be 0-9. If calculated as 10 then = 0
            int checksum = TEN - (tot % TEN);
            if (checksum == TEN) {
                checksum = 0;
            }

            return checksum == Integer.parseInt(isbn.substring(TWELVE));
        } catch (NumberFormatException nfe) {
            //to catch invalid ISBNs that have non-numeric characters in them
            return false;
        }
    }


    private Token generateToken(User user) {
        String userName = user.getUserName();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        int age = user.getAge();
        String time = System.currentTimeMillis() + "";
        String timeandPassword = time + password;
        String passAndTimeHash = Math.abs((timeandPassword.hashCode())) + "";
        String tokenstr = userName + "-" + passAndTimeHash + "-" + firstName + "-" + lastName + "-" + age;
        return new Token(tokenstr);
    }
}

