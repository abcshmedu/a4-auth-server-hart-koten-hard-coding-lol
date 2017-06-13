package edu.hm.cs.swa.authorization;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by CaptainEinsicht on 13.06.2017.
 * Alle Rechte vorbehalten bei Missbrauch wird zurück Missbraucht
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthServiceResult {

    OK(200, "Everything alright."),
    NAME_PASSWORD(400, "Name or password failed."),
    TOKEN(401, "Token is invalid."),
    ERROR(402, "Total disaster. Sad.");

    /**
     *
     */
    private int code;

    /**
     *
     */
    private String msg;

    /**
     * Constructor for enum.
     * @param code code number.
     * @param msg msg passed.
     */
    AuthServiceResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Returns code.
     * @return code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns msg.
     * @return msg.
     */
    public String getMsg() {
        return msg;
    }
}
