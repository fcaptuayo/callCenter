package com.almundo.call_center.core;

import com.almundo.call_center.domain.Employee;
import com.almundo.call_center.domain.Manager;
import com.almundo.call_center.domain.Operator;
import com.almundo.call_center.domain.Supervisor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Dispatcher is responsible the dispatchCall & create executorService.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public class EmployeeProducer {

    /**
     * Generator the EmployeesQueue whit operators number, supervisors number, mangers number.
     *
     * @param operatorsNumbers
     * @param supervisorsNumber
     * @param managersNumber
     * @return
     * @throws InterruptedException
     */
    public BlockingQueue<Employee> loadEmployeesQueue(int operatorsNumbers, int supervisorsNumber, int managersNumber) throws InterruptedException {

        BlockingQueue<Employee> employeeQueue = new PriorityBlockingQueue<>(operatorsNumbers + supervisorsNumber + managersNumber, new EmployeeComparator());

        for (int i = 0; i < operatorsNumbers; i++) {
            employeeQueue.put(new Operator(getIdEmployee()));
        }

        for (int i = 0; i < supervisorsNumber; i++) {
            employeeQueue.put(new Supervisor(getIdEmployee()));
        }

        for (int i = 0; i < managersNumber; i++) {
            employeeQueue.put(new Manager(getIdEmployee()));
        }
        return employeeQueue;
    }

    /**
     * Generate ID random.
     *
     * @return ID .
     */
    private int getIdEmployee() {
        return (int) (Math.random() * (100 - 200 + 1) + 100);
    }

}
