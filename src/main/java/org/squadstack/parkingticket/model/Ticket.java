package org.squadstack.parkingticket.model;

import lombok.Data;

@Data
public class Ticket {
    private int age;
    private String registrationNo;

    public Ticket(int age,String registrationNo){
        this.age=age;
        this.registrationNo = registrationNo;
    }
}
