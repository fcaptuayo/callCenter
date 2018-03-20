package com.almundo.call_center.core;

import com.almundo.call_center.domain.Call;
import com.almundo.call_center.domain.Employee;
import com.almundo.call_center.domain.enumerator.CallState;
import com.almundo.call_center.exception.InternalServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Runnable by Call Processor .
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public class CallProcessor implements Runnable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final BlockingQueue<Call> callQueue;
    private final BlockingQueue<Employee> employeesQueue;

    /**
     * Constructor by CallProcessor, required CallQueue, EmployeesQueue .
     *
     * @param callQueue
     * @param employeesQueue
     */
    public CallProcessor(BlockingQueue<Call> callQueue, BlockingQueue<Employee> employeesQueue) {
        this.callQueue = callQueue;
        this.employeesQueue = employeesQueue;
    }

    /**
     * Method execute in thread, this remove call of the CallQueue when execute success .
     */
    public void run() {
        int processTime = (int) ((Math.random() * (5 - 10 + 1) + 10) * 1000);
        while (!callQueue.isEmpty()) {
            if (!employeesQueue.isEmpty()) {
                try {
                    Employee employee = employeesQueue.poll(1, TimeUnit.SECONDS);
                    Call call = callQueue.poll(1, TimeUnit.SECONDS);
                    if (employee != null) {
                        call.setEmployee(employee);
                        call.setCallState(CallState.DONE);
                        employee.addCallsCounter();
                        Thread.sleep(processTime);
                        employeesQueue.put(employee);
                        log.warn("Successfully Processed Call. PROCESS_TIME:" + processTime);
                        log.warn("Successfully Processed Call. EMPLOYEE => ID:" + employee.getId() + " EMPLOYEE_TYPE:" + employee.getEmployeeType().getTitle() + " CALLS_COUNTER:" + employee.getCallsCounter());
                    } else if (call != null) {
                        call.setCallState(CallState.ERROR);
                        callQueue.put(call);
                        log.error("Not Available Employees for response of the call.");
                    }
                } catch (InterruptedException e) {
                    new InternalServiceException("Internal Server Error", e);
                }
            }
        }
    }
}
