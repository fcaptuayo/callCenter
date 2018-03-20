package com.almundo.call_center.core;

import com.almundo.call_center.domain.Call;
import com.almundo.call_center.domain.Employee;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * Dispatcher is responsible the dispatchCall & create executorService.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public class Dispatcher {
    private final ExecutorService executor;

    /**
     * Constructor of Dispatcher
     *
     * @param executor
     */
    public Dispatcher(ExecutorService executor) {
        this.executor = executor;
    }

    /**
     * @param callQueue
     * @param employeesQueue
     */
    public void dispatchCall(BlockingQueue<Call> callQueue, BlockingQueue<Employee> employeesQueue) {
        CallProcessor callProcessor = new CallProcessor(callQueue, employeesQueue);
        executor.submit(callProcessor);
    }
}
