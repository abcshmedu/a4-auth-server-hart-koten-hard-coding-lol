package edu.hm.cs.swa.controller;

import javax.ws.rs.core.Response.Status;

/**
 * Created by CaptainEinsicht on 03.05.2017. Alle Rechte vorbehalten bei
 * Missbrauch wird zurück Missbraucht
 */
public enum MediaServiceResult {

    OK(Status.OK.getStatusCode(), Status.OK), INVALID_ISBN(Status.NOT_ACCEPTABLE.getStatusCode(),
            Status.NOT_ACCEPTABLE), ISBN_TAKEN(301, Status.GONE), AUTHOR_OR_TITLE_MISSING(
                    Status.PARTIAL_CONTENT.getStatusCode(), Status.PARTIAL_CONTENT), ISBN_NOT_FOUND(
                            Status.NOT_FOUND.getStatusCode(), Status.NOT_FOUND), MODIFIY_ISBN(
                                    Status.NOT_MODIFIED.getStatusCode(), Status.NOT_MODIFIED), AUTHOR_AND_TITLE_MISSING(
                                            Status.NO_CONTENT.getStatusCode(), Status.NO_CONTENT);

    private Status status;
    private int code;

    MediaServiceResult(int code, Status status) {
        this.code = code;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
}