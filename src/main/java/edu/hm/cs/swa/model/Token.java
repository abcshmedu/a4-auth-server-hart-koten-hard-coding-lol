package edu.hm.cs.swa.model;

/**
 * Created by CaptainEinsicht on 23.05.2017.
 * Alle Rechte vorbehalten bei Missbrauch wird zurück Missbraucht
 */
public class Token {

    private final String tokenstr;

    private long timestamp;


    /**
     * constructor for class token.
     *
     * @param tokenstr the tokenstring.
     */
    public Token(final String tokenstr) {
        timestamp = System.currentTimeMillis();
        this.tokenstr = tokenstr;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        final Token token = (Token) o;

        return getTokenstr().equals(token.getTokenstr());
    }


    @Override
    public int hashCode() {
        return getTokenstr().hashCode();
    }


    /**
     * getter.
     *
     * @return tokenstr.
     */
    public String getTokenstr() {
        return tokenstr;
    }


    public long getTimestamp() {
        return timestamp;
    }
}
