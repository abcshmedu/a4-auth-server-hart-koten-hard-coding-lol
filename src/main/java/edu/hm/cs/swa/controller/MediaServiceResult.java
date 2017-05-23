package edu.hm.cs.swa.controller;

import javax.ws.rs.core.Response.Status;

/**
 * Created by CaptainEinsicht on 03.05.2017. Alle Rechte vorbehalten bei
 * Missbrauch wird zurück Missbraucht
 */
public enum MediaServiceResult {

    OK(Status.OK.getStatusCode(), "OK."), INVALID_ISBN(Status.NOT_ACCEPTABLE.getStatusCode(),
            "Your ISBN-13 is invalid."), ISBN_TAKEN(Status.GONE.getStatusCode(),
            "This ISBN-13 is already taken."), AUTHOR_OR_TITLE_MISSING(Status.PARTIAL_CONTENT.getStatusCode(),
            "Author or title is missing"), ISBN_NOT_FOUND(Status.NOT_FOUND.getStatusCode(),
            "ISBN could not be found."), MODIFIY_ISBN(Status.NOT_MODIFIED.getStatusCode(),
            "Your ISBN-13 needs to be modified."), AUTHOR_AND_TITLE_MISSING(
            Status.NO_CONTENT.getStatusCode(), "Author and title are missing.");

    private String status;

    private int code;


    /**
     * Custom constructor.
     *
     * @param code   Code number.
     * @param status Status report.
     */
    MediaServiceResult(int code, String status) {
        this.code = code;
        this.status = status;
    }


    /**
     * Getter for status report.
     *
     * @return Status.
     */
    public String getStatus() {
        return status;
    }


    /**
     * Getter for code number.
     *
     * @return code number.
     */
    public int getCode() {
        return code;
    }
}
