package com.almundo.call_center.core;

import com.almundo.call_center.domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

@RunWith(SpringRunner.class)
public class DispatcherTest {

    private BlockingQueue<Call> call_queue;
    private BlockingQueue<Employee> employeeQueue;
    private Dispatcher dispatcher;
    private int callProcessCounter;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }


    /**
     * ExecutorService the test .
     *
     * @param executor
     */
    protected void implementAsDirectExecutor(ExecutorService executor) {
        callProcessCounter = 0;
        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation)
                    throws Exception {
                Object[] args = invocation.getArguments();
                Runnable runnable = (Runnable) args[0];
                runnable.run();
                callProcessCounter++;
                return null;
            }
        }).when(executor).submit(any(Runnable.class));
    }

    @Mock
    private ExecutorService mockExecutor;

    @Before
    public void initExecutor() throws Exception {
        mockExecutor = Executors.newFixedThreadPool(10);
    }

    @Before
    public void setUp() throws InterruptedException {

        implementAsDirectExecutor(mockExecutor);

        dispatcher = new Dispatcher(mockExecutor);
        call_queue = new LinkedBlockingQueue<>();

        employeeQueue = new PriorityBlockingQueue<>(10, new EmployeeComparator());
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

    }

    /**
     * Test of the Success Process the 20 Call .
     *
     * @throws InterruptedException
     */
    @Test
    public void processSuccessWith20Calls() throws InterruptedException {

        callProcessCounter = 0;
        call_queue.put(new Call(23221L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23222L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23223L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23224L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23225L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23226L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23227L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23228L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23229L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23230L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23231L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23232L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23233L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23234L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23235L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23236L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23237L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23238L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23239L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23240L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        Assert.assertEquals(20, callProcessCounter);
    }

    /**
     * Test of the Success Process the 10 Call .
     *
     * @throws InterruptedException
     */
    @Test
    public void processSuccessWith10Calls() throws InterruptedException {
        callProcessCounter = 0;
        call_queue.put(new Call(23221L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23222L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23223L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23224L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23225L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23226L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23227L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23228L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23229L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        call_queue.put(new Call(23230L));
        dispatcher.dispatchCall(call_queue, employeeQueue);
        Assert.assertEquals(10, callProcessCounter);
    }
}
