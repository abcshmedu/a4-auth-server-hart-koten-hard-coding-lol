package edu.hm.cs.swa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.cs.swa.authorization.AuthServiceImpl;
import edu.hm.cs.swa.model.Book;
import edu.hm.cs.swa.model.Disc;
import edu.hm.cs.swa.model.Medium;
import edu.hm.cs.swa.model.Token;
import org.json.JSONArray;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST-API for ShareIt Service.
 *
 * @author Johannes Seidel, Michael Reile.
 */
@Singleton
@Path("/media")
public class MediaResource {

    private MediaService ms = new MediaServiceImpl();


    /**
     * Default c'tor.
     */
    public MediaResource() {
    }


    /**
     * Creates a book.
     *
     * @param book  Book that will be created.
     * @param token some token.
     * @return Response indicating success or failure.
     */
    @POST
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book, Token token) {
        MediaServiceResult msr;
        if (!AuthServiceImpl.tokenIsValid(token)) {
            msr = MediaServiceResult.AUTHORIZATION;
        } else {
            msr = ms.addBook(book);
        }

        return Response.status(msr.getCode()).build();
    }


    /**
     * Get a specific book, identified by its isbn.
     *
     * @param isbn  isbn of desired book.
     * @param token some token
     * @return Response with status code and book as json.
     */
    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn, Token token) {
        if (!AuthServiceImpl.tokenIsValid(token)) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
        }

        final Medium searchedBook = ms.getBook(isbn);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(searchedBook);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }


    /**
     * Get all the books available.
     *
     * @param token some token.
     * @return Response indicating success or failure.
     */
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(Token token) {
        if (!AuthServiceImpl.tokenIsValid(token)) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
        }

        //return (Book[]) ms.getBooks();
        Book[] allBooks = ms.getBooks();

        JSONArray jsonArray = new JSONArray();
        ObjectMapper mapper = new ObjectMapper();

        try {
            String result = mapper.writeValueAsString(allBooks);
            return Response.status(Response.Status.OK).entity(result).build();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Response.status(Response.Status.OK).entity(jsonArray.toString()).build();
    }


    /**
     * Update an existing book.
     *
     * @param book  Book that needs to be updated.
     * @param token some token
     * @return Response indicating success or failure.
     */
    @PUT
    @Path("/books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, Token token) {
        if (!AuthServiceImpl.tokenIsValid(token)) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
        }
        MediaServiceResult msr = ms.updateBook(book);

        return Response.status(msr.getCode()).entity(msr.getStatus()).build();
    }


    /**
     * Create a new disc.
     *
     * @param disc  Disc that will be created.
     * @param token some token
     * @return Status code indicating success or failure.
     */
    @POST
    @Path("/discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc disc, Token token) {
        if (!AuthServiceImpl.tokenIsValid(token)) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
        }
        MediaServiceResult msr = ms.addDisc(disc);

        return Response.status(msr.getCode()).build();
    }


    /**
     * Get a specific disc, identified by its barcode.
     *
     * @param barcode Barcode of the desired disc.
     * @param token   some token
     * @return Status code indicating success or failure.
     */
    @GET
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode, Token token) {
        if (!AuthServiceImpl.tokenIsValid(token)) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
        }

        final Medium searchedDisc = ms.getDisc(barcode);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(searchedDisc);
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }


    /**
     * Get a list of available discs.
     *
     * @param token some token
     * @return Response indicating success or failure.
     */
    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(Token token) {
        if (!AuthServiceImpl.tokenIsValid(token)) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
        }

        final Disc[] allDiscs = (Disc[]) ms.getDiscs();

        JSONArray jsonArray = new JSONArray();

        for (Disc disc : allDiscs) {
            jsonArray.put(disc.getTitle());
        }
        return Response.status(Response.Status.OK).entity(jsonArray.toString()).build();
    }


    /**
     * Update an existing disc.
     *
     * @param disc  Disc that needs to be updated.
     * @param token some token
     * @return Response indicating success or failure.
     */
    @PUT
    @Path("/discs/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, Token token) {
        if (!AuthServiceImpl.tokenIsValid(token)) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
        }
        MediaServiceResult msr = ms.updateDisc(disc);

        return Response.status(msr.getCode()).entity(msr.getStatus()).build();
    }

}
