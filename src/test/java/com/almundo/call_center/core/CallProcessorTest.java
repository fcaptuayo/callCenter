package com.almundo.call_center.core;

import com.almundo.call_center.domain.*;
import com.almundo.call_center.domain.enumerator.CallState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CallProcessorTest {

    private CallProcessor callProcessor;
    private Call call;
    private BlockingQueue<Call> call_queue;
    private BlockingQueue<Employee> employeeQueue;
    private int counterError;

    @Before
    public void setUp() throws InterruptedException {
        counterError = 0;
        employeeQueue = spy(new PriorityBlockingQueue<>(10, new EmployeeComparator()));
        employeeQueue.put(new Operator(1));
        employeeQueue.put(new Operator(2));
        employeeQueue.put(new Operator(3));
        employeeQueue.put(new Operator(4));
        employeeQueue.put(new Operator(5));
        employeeQueue.put(new Supervisor(6));
        employeeQueue.put(new Supervisor(7));
        employeeQueue.put(new Supervisor(8));
        employeeQueue.put(new Supervisor(9));
        employeeQueue.put(new Manager(10));

        call = spy(new Call(23221L));
        call_queue = spy(new LinkedBlockingQueue<>());
        call_queue.put(call);
        call_queue.put(new Call(23222L));
        call_queue.put(new Call(23223L));
        call_queue.put(new Call(23224L));
        call_queue.put(new Call(23225L));
        call_queue.put(new Call(23226L));
        call_queue.put(new Call(23227L));
        call_queue.put(new Call(23228L));
        call_queue.put(new Call(23229L));
        call_queue.put(new Call(23230L));
    }

    /**
     * Test validate complete call process success,
     *
     * @throws InterruptedException
     */
    @Test
    public void processSuccess() throws InterruptedException {
        this.counterError = 0;
        CallProcessor callProcessorLocal = new CallProcessor(call_queue, employeeQueue);
        callProcessorLocal.run();
        verify(call, never()).setCallState(CallState.ERROR);
        verify(call, times(1)).setCallState(CallState.DONE);
        Assert.assertEquals(new LinkedBlockingQueue<>().size(), call_queue.size());
    }

    /**
     * Test validate complete call process success, in this Test contemplate one error in process.
     *
     * @throws InterruptedException
     */
    @Test
    public void processSuccessWithOneErrorNotResponsePoll() throws InterruptedException {

        this.counterError = 0;
        CallProcessor callProcessorLocal = new CallProcessor(call_queue, employeeQueue);
        Mockito.when(employeeQueue.poll(1, TimeUnit.SECONDS)).then(new Answer<Employee>() {
            public Employee answer(InvocationOnMock invocation) {
                Employee employee = employeeQueue.poll();
                if (counterError == 0) {
                    counterError++;
                    return null;
                } else {
                    return employee;
                }
            }
        });
        callProcessorLocal.run();
        verify(call, times(1)).setCallState(CallState.ERROR);
        verify(call, times(1)).setCallState(CallState.DONE);
        Assert.assertEquals(new LinkedBlockingQueue<>().size(), call_queue.size());
    }

    /**
     * Test validate complete call process success, in this Test contemplate one InterruptedException in process.
     *
     * @throws InterruptedException
     */
    @Test
    public void processSuccessWithOneInterruptedExceptionResponsePoll() throws InterruptedException {

        this.counterError = 0;
        CallProcessor callProcessorLocal = new CallProcessor(call_queue, employeeQueue);
        Mockito.when(employeeQueue.poll(1, TimeUnit.SECONDS)).then(new Answer<Employee>() {
            public Employee answer(InvocationOnMock invocation) throws InterruptedException {
                Employee employee = employeeQueue.poll();
                if (counterError == 0) {
                    counterError++;
                    throw new InterruptedException();
                } else {
                    return employee;
                }
            }
        });
        callProcessorLocal.run();
        verify(call, never()).setCallState(CallState.ERROR);
        verify(call, times(1)).setCallState(CallState.DONE);
        Assert.assertEquals(new LinkedBlockingQueue<>().size(), call_queue.size());
    }

}
