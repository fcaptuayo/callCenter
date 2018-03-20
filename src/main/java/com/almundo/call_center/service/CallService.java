package com.almundo.call_center.service;

/**
 * Interface the call service.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
public interface CallService {

    /**
     * Method for process call .
     *
     * @return response the process call .
     * @throws InterruptedException
     */
    String process() throws InterruptedException;

}