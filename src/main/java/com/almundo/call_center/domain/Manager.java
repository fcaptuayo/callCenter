package com.almundo.call_center.domain;

import com.almundo.call_center.domain.enumerator.EmployeeType;

/**
 * Representation of the Manager.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public class Manager extends Employee {

    /**
     * Constructor general of the Manager .
     */
    public Manager(long id) {
        super(id, EmployeeType.MANAGER);
    }

}
