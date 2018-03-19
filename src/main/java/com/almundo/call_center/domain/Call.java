package com.almundo.call_center.domain;

import com.almundo.call_center.domain.enumerator.CallState;

/**
 * Representation of call.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public class Call {

    private long timeStamp;
    private Employee employee;
    private CallState callState;

    /**
     * Constructor general of the Call .
     */
    public Call() {
    }

    /**
     * Constructor of the Call with specific timeStamp .
     */
    public Call(long timeStamp) {
        super();
        this.timeStamp = timeStamp;
        this.callState = CallState.NEW;
    }

    // Generic getter and setter.

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public CallState getCallState() {
        return callState;
    }

    public void setCallState(CallState callState) {
        this.callState = callState;
    }
}
