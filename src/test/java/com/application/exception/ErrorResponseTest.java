package com.application.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class ErrorResponseTest {
    @InjectMocks
    private ErrorResponse errorResponse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getErrorCodeTest()
    {
        errorResponse.getErrorCode();
    }

    @Test
    public void setErrorCodeTest()
    {
        errorResponse.setErrorCode(1);
    }

    @Test
    public void getErrorMessage()
    {
        errorResponse.getErrorMessage();
    }

    @Test
    public void setErrorMessageTest()
    {
        errorResponse.setErrorMessage("test");
    }



}