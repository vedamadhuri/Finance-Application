package com.application.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class ExcessAmountExceptionTest {

    @InjectMocks
    private ExcessAmountException excessAmountException;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void ExcessAmountExceptionWithSingleArgumentTest()
    {
       new ExcessAmountException("test");
    }

    @Test
    public void ExcessAmountExceptionWithDoubleArgumentTest()
    {
       // final OngoingStubbing<Object> objectOngoingStubbing = Mockito.when(super("message")).thenReturn("exception");
        new ExcessAmountException("test","exception");
    }
}