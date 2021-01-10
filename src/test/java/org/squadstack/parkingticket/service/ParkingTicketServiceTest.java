package org.squadstack.parkingticket.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@RunWith(MockitoJUnitRunner.class)
public class ParkingTicketServiceTest {

    @InjectMocks
    ParkingTicketService testObj;
    @Test
    public void readFile() throws IOException {
        String[] output={"Created parking of 6 slots",
                "Car with vehicle registration number 'KA-01-HH-1234' has been parked at slot number 1",
                "Car with vehicle registration number 'PB-01-HH-1234' has been parked at slot number 2",
                "1,2","Car with vehicle registration number 'PB-01-TG-2341' has been parked at slot number 3",
                "2",
                "Slot number 2 vacated 'PB-01-HH-1234' left the space, the driver of the car was of age 21",
                "Car with vehicle registration number 'HR-29-TG-3098' has been parked at slot number 2"};
        Assert.assertArrayEquals(output,testObj.process(Objects.requireNonNull(inputFile("proto/mockinput3.txt"))).toArray());

    }
    @Test
    public void readFile_Exceptions() throws IOException {
        String[] output={"Created parking of 6 slots","Car with vehicle registration number 'KA-01-HH-1234' has been parked at slot number 1",
                "Car with vehicle registration number 'PB-01-HH-1232' has been parked at slot number 2",
                "Car with vehicle registration number 'PB-01-HH-1235' has been parked at slot number 3",
                "Car with vehicle registration number 'PB-01-HH-1239' has been parked at slot number 4",
                "Car with vehicle registration number 'PB-01-HH-1278' has been parked at slot number 5",
                "Car with vehicle registration number 'PB-01-HH-1211' has been parked at slot number 6",
                "Parking Full","1,2,3,4,5,6","Parking Full","No car found with registration no 'PB-01-HH-0000'",
                "Slot number 2 vacated 'PB-01-HH-1232' left the space, the driver of the car was of age 21",
                "Slot 2 already vacant",
                "Car with vehicle registration number 'HR-29-TG-3098' has been parked at slot number 2",
                "Unrecognised command - JIBRISHCOMMAND"};
        Assert.assertArrayEquals(output,testObj.process(Objects.requireNonNull(inputFile("proto/mockinput2.txt"))).toArray());
    }
    @Test
    public void readFile_emptyFile() throws IOException {
        Assert.assertEquals(0,testObj.process(inputFile("proto/mockinput1.txt")).toArray().length);
    }
    public static MultipartFile inputFile(String path) {
        try {
            String name = "mockinput1.txt";
            String originalFileName = "mockinput1.txt";
            String contentType = "text/plain";
            File file=new ClassPathResource(path).getFile();
            byte[] content = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            return  new MockMultipartFile(name,
                    originalFileName, contentType, content);
        }
        catch (Exception e){}
        return null;
    }
}