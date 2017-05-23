package edu.hm.cs.swa.model;

/**
 * Copy class for a Medium.
 *
 * @author Johannes Seidel, Michael Reile.
 */
public class Copy {

    private final Medium medium;

    private String owner;


    /**
     * Custom constructor.
     *
     * @param medium Medium that is supposed to be copied.
     * @param owner  Owner of that medium.
     */
    public Copy(final Medium medium, final String owner) {
        this.medium = medium;
        this.owner = owner;
    }


    /**
     * Getter for medium.
     *
     * @return medium.
     */
    public Medium getMedium() {
        return medium;
    }


    /**
     * Getter for owner.
     *
     * @return owner.
     */
    public String getOwner() {
        return owner;
    }
}
