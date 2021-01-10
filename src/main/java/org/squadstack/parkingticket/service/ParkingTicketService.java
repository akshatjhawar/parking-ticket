package org.squadstack.parkingticket.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.squadstack.parkingticket.model.ParkingLot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.squadstack.parkingticket.common.Constants.CREATE_PARKING;
import static org.squadstack.parkingticket.common.Constants.SLOT_NO_PLACEHOLDER;

@Service
public class ParkingTicketService {
    public static final String CREATE_PARKING_LOT = "Create_parking_lot";
    public static final String PARK = "Park";
    public static final String SLOT_NUMBERS_FOR_DRIVER_OF_AGE = "Slot_numbers_for_driver_of_age";
    public static final String SLOT_NUMBER_FOR_CAR_WITH_NUMBER = "Slot_number_for_car_with_number";
    public static final String LEAVE = "Leave";
    public static final String VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE = "Vehicle_registration_number_for_driver_of_age";
    public static final String UNRECOGNISED_COMMAND = "Unrecognised command - ";

    private ParkingLot parkingLot;

    public List<String> process(MultipartFile file) throws IOException {
        List<String> output = Arrays.stream(new String(file.getBytes(), StandardCharsets.UTF_8).split("\\n")).filter(line ->
                !StringUtils.isBlank(line.trim())).map(line -> callMethod(Arrays.asList(line.split(" ")))).
                filter(StringUtils::isNotBlank).collect(Collectors.toList());
        output.forEach(System.out::println);
        return output;
    }

    private String callMethod(List<String> line) {
        try {
            switch (line.get(0)) {
                case CREATE_PARKING_LOT:
                    parkingLot = new ParkingLot(Integer.parseInt(line.get(1).trim()));
                    return CREATE_PARKING.replace(SLOT_NO_PLACEHOLDER, line.get(1).trim());
                case PARK:
                    return parkingLot.newCarParking(Integer.parseInt(line.get(3).trim()), line.get(1).trim());
                case SLOT_NUMBERS_FOR_DRIVER_OF_AGE:
                    return parkingLot.getSlotsByAge(Integer.parseInt(line.get(1).trim()));
                case SLOT_NUMBER_FOR_CAR_WITH_NUMBER:
                    return parkingLot.getSlotsByRegistrationNo(line.get(1).trim());
                case LEAVE:
                    return parkingLot.removeCarFromParking(Integer.parseInt(line.get(1).trim()));
                case VEHICLE_REGISTRATION_NUMBER_FOR_DRIVER_OF_AGE:
                    return parkingLot.getRegistrationNoByAge(Integer.parseInt(line.get(1).trim()));
                default:
                    return UNRECOGNISED_COMMAND+StringUtils.join(line," ").trim();
            }
        } catch (Exception e) {
            return "Encountered error while executing command" + e.getMessage();
        }
    }

}
