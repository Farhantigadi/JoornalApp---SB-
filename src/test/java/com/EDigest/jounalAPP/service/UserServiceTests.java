package com.EDigest.jounalAPP.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTests {

   @Autowired
   UserService  service;


   @ParameterizedTest
   @CsvSource({
           "1,1,3",
           "2,2,4",
           "2,8,1"
           })
   public void testservice(int a, int b,int expected){
       assertEquals(expected,a,b);
   }

    @Test
    public void testservice(){
        assertNotNull(service.getAllUsers());
    }
}
