package com.example.week4;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cashier {
    @RequestMapping(value = "/getChange/{n1}", method = RequestMethod.GET)
    public Change getChange(@PathVariable("n1") double n1){
        Change c = new Change();
        while (n1 > 0){
            if (n1 > 1000){
                n1 -= 1000;
                c.setB1000(c.getB1000()+1);
            }
            else if (n1 > 500){
                n1 -= 500;
                c.setB500(c.getB500()+1);
            }
            else if (n1 > 100){
                n1 -= 100;
                c.setB100(c.getB100()+1);
            }
            else if (n1 > 20){
                n1 -= 20;
                c.setB20(c.getB20()+1);
            }
            else if (n1 > 10){
                n1 -= 10;
                c.setB10(c.getB10()+1);
            }
            else if (n1 > 5){
                n1 -= 5;
                c.setB5(c.getB5()+1);
            }
            else {
                n1 -= 1;
                c.setB1(c.getB1()+1);
            }
        }
        return c;
    }
}
