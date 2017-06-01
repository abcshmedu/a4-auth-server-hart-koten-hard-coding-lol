package edu.hm.cs.swa.Tests;

import edu.hm.cs.swa.authorization.AuthServiceImpl;
import edu.hm.cs.swa.controller.MediaResource;
import edu.hm.cs.swa.controller.MediaServiceResult;
import edu.hm.cs.swa.model.Book;
import edu.hm.cs.swa.model.Disc;
import edu.hm.cs.swa.model.Token;
import edu.hm.cs.swa.model.User;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * just testing whether the auth works.
 */
public class MediaServiceTest {

    private final User kevin = new User("Chicksterminator", "Kevin", "Penopt", "20quickSniperLord05", 12);

    private final MediaResource mr = new MediaResource();

    private final AuthServiceImpl asi = new AuthServiceImpl();

    private final Book book = new Book("Some", "978-3-12-732320-7", "Any");

    private final Token failToken = new Token("some fail token");

    private final Disc disc = new Disc("123456789", "anybody", 18, "something");


    /**
     * before the tests start.
     */
    @Before
    public void initialze() {
        asi.addUser(kevin);
        asi.generateToken(kevin);
        mr.createBook(book, asi.generateToken(kevin));
        mr.createDisc(disc, kevin.getUserToken());
    }


    /**
     * should create a new book.
     */
    @Test
    public void createBookTestSuccess() {
        MediaServiceResult result = MediaServiceResult.ISBN_TAKEN;
        String expected = Response.status(result.getCode()).build().toString();
        Response response = mr.createBook(book, kevin.getUserToken());
        assertEquals(response.toString(), expected);
    }


    /**
     * user is not allowed to create a new book.
     */
    @Test
    public void createBookTestFail() {
        Response response = mr.createBook(book, failToken);
        assertEquals(response.toString(), Response.status(Response.Status.UNAUTHORIZED).build().toString());
    }


    /**
     * gets book inserted in inittialze.
     */
    @Test
    public void getBookTestSuccess() {
        Response response = mr.getBook("978-3-12-732320-7", kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     * user is not allowed to get a book.
     */
    @Test
    public void getBookTestFail() {
        Response response = mr.getBook("978-3-12-732320-7", failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     * should return all books which is just one.
     */
    @Test
    public void getBooksTestSuccess() {
        Response response = mr.getBooks(kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     * user is not allowed to get all books.
     */
    @Test
    public void getBooksTestFail() {
        Response response = mr.getBooks(failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     * should update the given book user is allowed.
     */
    @Test
    public void updateBookTestSuccess() {
        Response response = mr.updateBook(book, kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     * userr is not allowed to update a book.
     */
    @Test
    public void updateBookTestFail() {
        Response response = mr.updateBook(book, failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     * should create a new disc user is allowed to do it.
     */
    @Test
    public void createDisTestSuccess() {

        Response response = mr.createDisc(disc, kevin.getUserToken());
        assertEquals(response.getStatus(), MediaServiceResult.ISBN_TAKEN.getCode());
    }


    /**
     * user is not allowed to create a new disc should not work.
     */
    @Test
    public void createDiscTestFail() {
        Response response = mr.createDisc(disc, failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     * should return a disc from the list useris allowed to do it.
     */
    @Test
    public void getDiscTestSuccess() {
        Response response = mr.getDisc("123456789", kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     * user is not allowed to get a disc here should not work.
     */
    @Test
    public void getDiscTestFail() {
        Response response = mr.getDisc("123456789", failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     * should return all disc user is allowed.
     */
    @Test
    public void getDiscsTestSuccess() {
        Response response = mr.getDiscs(kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     * should not work to get all discs user is not allowed to do it.
     */
    @Test
    public void getDiscsTestFail() {
        Response response = mr.getDiscs(failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     * should update the give ndisc user is allowed to do it.
     */
    @Test
    public void updateDiscTestSuccess() {
        Response response = mr.updateDisc(disc, kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     * should not work user is not allowed to update a disc.
     */
    @Test
    public void updateDiscTestFail() {
        Response response = mr.updateDisc(disc, failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }

}
