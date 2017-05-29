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

public class MediaServiceTest {

    private final User kevin = new User("Chicksterminator", "Kevin", "Penopt", "20quickSniperLord05", 12);

    private final MediaResource mr = new MediaResource();

    private final AuthServiceImpl asi = new AuthServiceImpl();

    private final Book book = new Book("Some", "978-3-12-732320-7", "Any");

    private final Token failToken = new Token("some fail token");

    private final Disc disc = new Disc("123456789", "anybody", 18, "something");


    /**
     *
     */
    @Before
    public void initialze() {
        asi.addUser(kevin);
        asi.generateToken(kevin);
        mr.createBook(book, asi.generateToken(kevin));
        mr.createDisc(disc, kevin.getUserToken());
    }


    /**
     *
     */
    @Test
    public void createBookTestSuccess() {
        MediaServiceResult result = MediaServiceResult.ISBN_TAKEN;
        String expected = Response.status(result.getCode()).build().toString();
        Response response = mr.createBook(book, kevin.getUserToken());
        assertEquals(response.toString(), expected);
    }


    /**
     *
     */
    @Test
    public void createBookTestFail() {
        Response response = mr.createBook(book, failToken);
        assertEquals(response.toString(), Response.status(Response.Status.UNAUTHORIZED).build().toString());
    }


    /**
     *
     */
    @Test
    public void getBookTestSuccess() {
        Response response = mr.getBook("978-3-12-732320-7", kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void getBookTestFail() {
        Response response = mr.getBook("978-3-12-732320-7", failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void getBooksTestSuccess() {
        Response response = mr.getBooks(kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void getBooksTestFail() {
        Response response = mr.getBooks(failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void updateBookTestSuccess() {
        Response response = mr.updateBook(book, kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void updateBookTestFail() {
        Response response = mr.updateBook(book, failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void createDisTestSuccess() {

        Response response = mr.createDisc(disc, kevin.getUserToken());
        assertEquals(response.getStatus(), MediaServiceResult.ISBN_TAKEN.getCode());
    }


    /**
     *
     */
    @Test
    public void createDiscTestFail() {
        Response response = mr.createDisc(disc, failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void getDiscTestSuccess() {
        Response response = mr.getDisc("123456789", kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void getDiscTestFail() {
        Response response = mr.getDisc("123456789", failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void getDiscsTestSuccess() {
        Response response = mr.getDiscs(kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void getDiscsTestFail() {
        Response response = mr.getDiscs(failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void updateDiscTestSuccess() {
        Response response = mr.updateDisc(disc, kevin.getUserToken());
        assertEquals(response.getStatus(), Response.status(Response.Status.OK).build().getStatus());
    }


    /**
     *
     */
    @Test
    public void updateDiscTestFail() {
        Response response = mr.updateDisc(disc, failToken);
        assertEquals(response.getStatus(), Response.status(Response.Status.METHOD_NOT_ALLOWED).build().getStatus());
    }

}
