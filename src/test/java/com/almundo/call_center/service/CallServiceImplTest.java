package com.almundo.call_center.service;

import com.almundo.call_center.service.impl.CallServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
public class CallServiceImplTest {

    @TestConfiguration
    static class CallServiceImplTestContextConfiguration {
        @Bean
        public CallService callService() throws InterruptedException {
            return new CallServiceImpl();
        }
    }

    @Autowired
    private CallService callService;

    @Before
    public void setUp() {

    }

    /**
     * Test of the response of the CallService for process() .
     *
     * @throws InterruptedException
     */
    @Test
    public void processSuccess() throws InterruptedException {
        String response = callService.process();
        Assert.notNull(response);
    }
}


