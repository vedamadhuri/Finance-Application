package com.application.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomExceptionHandlerTest {


    @InjectMocks
    CustomExceptionHandler customExceptionHandler;
    private Exception exception;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void exceptionHandlerTest()
    {
    customExceptionHandler.exceptionHandler(exception);
    }

    @Test
    public  void ExcessAmountExceptionHandlerTest()
    {
        customExceptionHandler.ExcessAmountExceptionHandler(exception);
    }

    @Test
    public void userNotFoundExceptionHandlerTest()
    {
        customExceptionHandler.userNotFoundExceptionHandler(exception);
    }
}