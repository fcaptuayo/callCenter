package com.almundo.call_center.domain.enumerator;

/**
 * Representation of Employees Type of call center .
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public enum EmployeeType {
    OPERATOR(1, "Operador"),
    SUPERVISOR(2, "Supervisor"),
    MANAGER(3, "Gerente");

    private int priority;
    private String title;

    /**
     * Constructor general of the employee Type .
     *
     * @param priority
     * @param title
     */
    EmployeeType(int priority, String title) {
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
