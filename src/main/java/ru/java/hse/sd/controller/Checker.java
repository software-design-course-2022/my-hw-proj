package ru.java.hse.sd.controller;

/**
 * Stores information about checker.
 **/
public class Checker {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    /**
     * Creates new instance of Checker object.
     * Initialises id and code with the given values.
     *
     * @param id   checker id
     * @param code checker code
     **/
    public Checker(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public Checker() {}

    /**
     * Returns checker id.
     *
     * @return checker id as string
     **/
    public String getId() {
        return id;
    }

    /**
     * Returns checker code.
     *
     * @return checker code as string
     **/
    public String getCode() {
        return code;
    }
}
