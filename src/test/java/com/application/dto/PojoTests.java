package com.application.dto;

import com.application.model.*;
import com.application.reflectionUtils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class PojoTests {
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void allGettersInvocation() {
        ReflectionUtils.invoke(new TransactionHistoryDto());
        ReflectionUtils.invoke(new AdminDto());
        ReflectionUtils.invoke(new GroupDto());
        ReflectionUtils.invoke(new GroupUserDto());
        ReflectionUtils.invoke(new PaymentsDto());
        ReflectionUtils.invoke(new UserDto());
        ReflectionUtils.invoke(new TransactionHistory());
        ReflectionUtils.invoke(new Admin());
        ReflectionUtils.invoke(new User());
        ReflectionUtils.invoke(new Payments());
        ReflectionUtils.invoke(new Group());
        ReflectionUtils.invoke(new GroupUser());
    }

}