package com.almundo.call_center.domain;

import com.almundo.call_center.domain.enumerator.EmployeeType;

/**
 * Representation generic Employee.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public abstract class Employee {

    private long id;
    private EmployeeType employeeType;

    //counter by validate repartition of the call.
    private int callsCounter;

    /**
     * Constructor general of the Employee .
     */
    public Employee(long id, EmployeeType typeEmployee) {
        this.id = id;
        this.employeeType = typeEmployee;
        this.callsCounter = 0;
    }

    // Generic getter and setter.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public int getCallsCounter() {
        return callsCounter;
    }

    public int addCallsCounter() {
        return this.callsCounter++;
    }


}
