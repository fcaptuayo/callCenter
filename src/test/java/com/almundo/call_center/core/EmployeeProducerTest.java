package com.almundo.call_center.core;

import com.almundo.call_center.domain.Employee;
import com.almundo.call_center.domain.Manager;
import com.almundo.call_center.domain.Operator;
import com.almundo.call_center.domain.Supervisor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

@RunWith(SpringRunner.class)
public class EmployeeProducerTest {

    private EmployeeProducer employeeProducer;

    @Before
    public void before() {
        employeeProducer = new EmployeeProducer();
    }

    @Test
    public void loadEmployeesQueueSuccess() throws InterruptedException {
        BlockingQueue<Employee> employeeQueue = new PriorityBlockingQueue<>(10 + 5 + 1, new EmployeeComparator());
        employeeQueue.put(new Operator(1));
        employeeQueue.put(new Operator(2));
        employeeQueue.put(new Operator(3));
        employeeQueue.put(new Operator(4));
        employeeQueue.put(new Operator(5));
        employeeQueue.put(new Operator(6));
        employeeQueue.put(new Operator(7));
        employeeQueue.put(new Operator(8));
        employeeQueue.put(new Operator(9));
        employeeQueue.put(new Operator(10));
        employeeQueue.put(new Supervisor(11));
        employeeQueue.put(new Supervisor(12));
        employeeQueue.put(new Supervisor(13));
        employeeQueue.put(new Supervisor(14));
        employeeQueue.put(new Supervisor(15));
        employeeQueue.put(new Manager(16));

        BlockingQueue<Employee> response = employeeProducer.loadEmployeesQueue(10, 5, 1);

        org.junit.Assert.assertEquals(employeeQueue.size(), response.size());
    }


}
