package com.example.xmlPerson;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {

        // use xml
//        useXMLToGetBeans();
    }

    public static void useXMLToGetBeans() {
        final String xmlPath = "applicationContext.xml";

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

        System.out.println(applicationContext.getBean("person1"));
        System.out.println(applicationContext.getBean("person2"));
    }
}
