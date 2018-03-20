package com.almundo.call_center.controller;

import com.almundo.call_center.service.CallService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CallController.class)
public class CallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CallService service;

    /**
     * Test of response request /calls
     *
     * @throws Exception
     */
    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(service.process()).thenReturn("Hello Mock");
        this.mockMvc.perform(get("/calls")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Mock")));
    }

}
