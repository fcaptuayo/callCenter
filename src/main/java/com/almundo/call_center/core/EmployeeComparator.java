package com.almundo.call_center.core;

import com.almundo.call_center.domain.Employee;

import java.util.Comparator;

/**
 * Dispatcher is responsible the dispatchCall & create executorService.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public class EmployeeComparator implements Comparator<Employee> {

    /**
     * Comparator of priority of the Employees of Queue .
     *
     * @param e1
     * @param e2
     * @return position the Employees
     */
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getEmployeeType().getPriority() < e2.getEmployeeType().getPriority() ? -1 : e1.getEmployeeType().getPriority() > e2.getEmployeeType().getPriority() ? +1 : 0;
    }

}
