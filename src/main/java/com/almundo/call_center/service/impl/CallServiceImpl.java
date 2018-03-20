package com.almundo.call_center.service.impl;

import com.almundo.call_center.core.Dispatcher;
import com.almundo.call_center.core.EmployeeProducer;
import com.almundo.call_center.domain.Call;
import com.almundo.call_center.domain.Employee;
import com.almundo.call_center.service.CallService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Service for process request the calls.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
@Service("callService")
public class CallServiceImpl implements CallService {

    private final BlockingQueue<Employee> employeeQueue;

    private final static BlockingQueue<Call> CALL_QUEUE;
    private final static Dispatcher DISPATCHER;
    private final static EmployeeProducer EMPLOYEE_PRODUCER;
    private final static int THREADS_NUMBER;

    static {
        THREADS_NUMBER = 10;
        CALL_QUEUE = new LinkedBlockingQueue<>();
        EMPLOYEE_PRODUCER = new EmployeeProducer();

        //Instance Dispatcher with a maximum of threads .
        DISPATCHER = new Dispatcher(Executors.newFixedThreadPool(THREADS_NUMBER));
    }

    /**
     * Constructor of Impl CallServer
     *
     * @throws InterruptedException
     */
    public CallServiceImpl() throws InterruptedException {
        this.employeeQueue = EMPLOYEE_PRODUCER.loadEmployeesQueue(5, 3, 1);
    }

    /**
     * Response message of process call.
     *
     * @return response the process call .
     */
    @Override
    public String process() throws InterruptedException {
        Call call = new Call(new Date().getTime());
        CALL_QUEUE.put(call);
        DISPATCHER.dispatchCall(CALL_QUEUE, employeeQueue);
        return "Su llamada es siendo procesada, espere un momento. Gracias";
    }

}