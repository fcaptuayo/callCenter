package com.almundo.call_center.controller;

import com.almundo.call_center.service.CallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Controller for response request the calls.
 * Created by Fredy Gonzalo Captuayo Novoa on 19/03/18.
 */
@RestController()
public class CallController {

    /**
     * Service for call process .
     */
    @Inject
    private CallService callService;

    /**
     * Retrieve a call request and send it to the service to be handled .
     *
     * @return response the service .
     * @throws InterruptedException
     */
    @RequestMapping(value = "/calls", method = RequestMethod.GET)
    public ResponseEntity<?> getCallProcess() throws InterruptedException {
        return new ResponseEntity<>(this.callService.process(), HttpStatus.OK);
    }

}
