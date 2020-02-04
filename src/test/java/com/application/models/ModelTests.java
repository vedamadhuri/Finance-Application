package com.application.models;

import com.application.model.*;
import com.application.reflectionUtils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ModelTests {

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void allMethodsInvokation()
    {
        ReflectionUtils.invoke(new Admin());
        ReflectionUtils.invoke(new GroupUser());
        ReflectionUtils.invoke(new Group());
        ReflectionUtils.invoke(new Payments());
        ReflectionUtils.invoke(new TransactionHistory());
        ReflectionUtils.invoke(new User());
    }
}
