package com.almundo.call_center.domain.enumerator;

/**
 * Representation of Call States of call.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public enum CallState {

    //state of call new.
    NEW(1, "Nueva"),
    //state of call whit error.
    ERROR(2, "Nueva"),
    //state of call terminate.
    DONE(3, "Procesada");

    private int priority;
    private String title;

    /**
     * Constructor general of the call state
     *
     * @param priority
     * @param title
     */
    CallState(int priority, String title) {
        this.priority = priority;
        this.title = title;
    }

    // Generic getter.

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }
}
