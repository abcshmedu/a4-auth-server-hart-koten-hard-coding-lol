package edu.hm.cs.swa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.cs.swa.model.Book;
import edu.hm.cs.swa.model.Disc;
import edu.hm.cs.swa.model.Medium;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-API for ShareIt Service.
 *
 * @author Johannes Seidel, Michael Reile.
 */
@Path("media")
public class MediaResource {

    private MediaService ms = new MediaServiceImpl();


    /**
     * Default c'tor.
     */
    MediaResource() {
    }


    /**
     * Creates a book.
     *
     * @param book Book that will be created.
     * @return Response indicating success or failure.
     */
    @POST
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {

        MediaServiceResult msr = ms.addBook(book);

        return Response.status(msr.getCode()).build();
    }


    /**
     * Get a specific book, identified by its isbn.
     *
     * @param isbn isbn of desired book.
     * @return Response with status code and book as json.
     */
    @GET
    @Path("books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) {
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
     * @return Response indicating success or failure.
     */
    @GET
    @Path("books")
    //@Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        final Medium[] allBooks = ms.getBooks();

        JSONArray jsonArray = new JSONArray();
        ObjectMapper mapper = new ObjectMapper();

        List<Medium> allB = Arrays.stream(allBooks).collect(Collectors.toList());
        try {
            String result = mapper.writeValueAsString(allB);
            return Response.status(Response.Status.OK).entity(result).build();

        } catch (JsonProcessingException e) {
            e.printStackTrace();

        }

        for (Medium book : allBooks) {
            jsonArray.put(book.getTitle());
        }
        return Response.status(Response.Status.OK).entity(jsonArray.toString()).build();
    }


    /**
     * Update an existing book.
     *
     * @param book Book that needs to be updated.
     * @return Response indicating success or failure.
     */
    @PUT
    @Path("books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book) {
        MediaServiceResult msr = ms.updateBook(book);

        return Response.status(msr.getCode()).entity(msr.getStatus().toString()).build();
    }


    /**
     * Create a new disc.
     *
     * @param disc Disc that will be created.
     * @return Status code indicating success or failure.
     */
    @POST
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc disc) {
        MediaServiceResult msr = ms.addDisc(disc);

        return Response.status(msr.getCode()).build();
    }


    /**
     * Get a specific disc, identified by its barcode.
     *
     * @param barcode Barcode of the desired disc.
     * @return Status code indicating success or failure.
     */
    @GET
    @Path("discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode) {

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
     * @return Response indicating success or failure.
     */
    @GET
    @Path("discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {

        final Medium[] allDiscs = ms.getDiscs();

        JSONArray jsonArray = new JSONArray();

        for (Medium disc : allDiscs) {
            jsonArray.put(disc.getTitle());
        }
        return Response.status(Response.Status.OK).entity(jsonArray.toString()).build();
    }


    /**
     * Update an existing disc.
     *
     * @param disc Disc that needs to be updated.
     * @return Response indicating success or failure.
     */
    @PUT
    @Path("discs/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc) {
        MediaServiceResult msr = ms.updateDisc(disc);

        return Response.status(msr.getCode()).entity(msr.getStatus().toString()).build();
    }
}
