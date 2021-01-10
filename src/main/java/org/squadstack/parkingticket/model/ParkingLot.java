package org.squadstack.parkingticket.model;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.squadstack.parkingticket.common.Constants.*;

public class ParkingLot {
    private HashMap<Integer, Ticket> tickets;
    private PriorityQueue<Integer> emptySlots;

    public ParkingLot() { }

    public ParkingLot(int n) {
        tickets = new HashMap<>();
        emptySlots = new PriorityQueue();
        IntStream.range(1, n + 1).boxed().forEach(slot -> {
            tickets.put(slot, null);
            emptySlots.add(slot);
        });
    }

    public String newCarParking(int age, String registrationNo) {
        if (emptySlots.size() == 0) return "Parking Full";
        int n = emptySlots.poll();
        tickets.put(n, new Ticket(age, registrationNo));
        return PARKING_OUTPUT.replace(REGISTRATION_NO_PLACEHOLDER, registrationNo).
                replace(SLOT_NO_PLACEHOLDER, String.valueOf(n));
    }

    public String removeCarFromParking(int n) {
        if (Objects.isNull(tickets.get(n)))
            return SLOT_ALREADY_VACANT.replace(SLOT_NO_PLACEHOLDER, String.valueOf(n));
        String out = SLOT_VACATED.replace(SLOT_NO_PLACEHOLDER, String.valueOf(n)).
                replace(REGISTRATION_NO_PLACEHOLDER, tickets.get(n).getRegistrationNo()).
                replace(AGE_PLACEHOLDER, String.valueOf(tickets.get(n).getAge()));
        tickets.put(n, null);
        emptySlots.add(n);
        return out;
    }

    public String getSlotsByAge(int age) {
        return tickets.entrySet().stream().filter((key) -> key.getValue() != null &&
                tickets.get(key.getKey()).getAge() == age).
                map(map -> map.getKey().toString()).collect(Collectors.joining(","));

    }

    public String getSlotsByRegistrationNo(String registrationNo) {
        try {
            return tickets.entrySet().parallelStream().filter((entry) -> entry.getValue() != null &&
                    entry.getValue().getRegistrationNo().equals(registrationNo)).
                    findFirst().get().getKey().toString();
        } catch (Exception e) {
            return NO_CAR_FOUND.replace(REGISTRATION_NO_PLACEHOLDER, registrationNo);
        }
    }

    public String getRegistrationNoByAge(int age) {
        return tickets.entrySet().stream().filter(map -> map.getValue() != null && map.getValue().getAge() == age).
                map(map -> map.getValue().getRegistrationNo())
                .collect(Collectors.joining(","));
    }
}


