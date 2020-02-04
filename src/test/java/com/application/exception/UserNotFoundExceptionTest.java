package com.application.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class UserNotFoundExceptionTest {

    @InjectMocks
    private UserNotFoundException userNotFoundException;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public  void  getErrorMessageTest()
    {
        userNotFoundException.getErrorMessage();
    }

    @Test
    public void UserNotFoundExceptionWithSingleArgument()
    {
        new UserNotFoundException("test");
    }

    @Test
    public void UserNotFoundExceptionWithDoubleArguments()
    {
        new UserNotFoundException("test","sample");
    }
}