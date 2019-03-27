package com.example.spring_02;

import com.example.spring_02.model.Person;
import com.example.spring_02.model.Person1;
import com.example.spring_02.model.Person2;
import com.example.spring_02.model.Person3;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Spring02ApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private Person person;

    @Autowired
    private Person1 person1;

    @Autowired
    private Person2 person2;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testPerson(){
        log.info("----->"+person.toString()+"\n");
        log.info("----->"+person1.toString()+"\n");
        log.info("----->"+person2.toString()+"\n");
        Person3 person3 = (Person3) applicationContext.getBean("myperson");
        log.info("----->"+person3.toString()+"\n");
    }

}
