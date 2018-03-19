package com.almundo.call_center.domain;

import com.almundo.call_center.domain.enumerator.EmployeeType;

/**
 * Representation of the Operator.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public class Operator extends Employee {

    /**
     * Constructor general of the Operator .
     */
    public Operator(long id) {
        super(id, EmployeeType.OPERATOR);
    }

}
