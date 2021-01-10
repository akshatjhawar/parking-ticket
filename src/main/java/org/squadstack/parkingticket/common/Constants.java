package org.squadstack.parkingticket.common;

public class Constants {
    public static final String REGISTRATION_NO_PLACEHOLDER="%REGISTRATION_NO%";
    public static final String SLOT_NO_PLACEHOLDER="%SLOT_NO%";
    public static final String AGE_PLACEHOLDER="%AGE%";
    public static final String CREATE_PARKING="Created parking of "+SLOT_NO_PLACEHOLDER+" slots";
    public static final String SLOT_VACATED="Slot number "+SLOT_NO_PLACEHOLDER+" vacated '"
            +REGISTRATION_NO_PLACEHOLDER+ "' left the space, the driver of the car was of age "+AGE_PLACEHOLDER;
    public static final String PARKING_OUTPUT="Car with vehicle registration number '"+REGISTRATION_NO_PLACEHOLDER+
            "' has been parked at slot number "+SLOT_NO_PLACEHOLDER;
    public static final String SLOTS_FULl="No Slots are available";
    public static final String SLOT_ALREADY_VACANT="Slot "+SLOT_NO_PLACEHOLDER+" already vacant";
    public static final String NO_CAR_FOUND="No car found with registration no '"+REGISTRATION_NO_PLACEHOLDER+"'";
}
