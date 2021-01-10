package org.squadstack.parkingticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.squadstack.parkingticket.service.ParkingTicketService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 9600)
public class ParkingTicketController {
    @Autowired
    ParkingTicketService service;
    @RequestMapping(value = "/inputFile", method = RequestMethod.POST)
    public List<String> uploadFileHandler(@RequestParam("file") MultipartFile file) throws IOException {
        return service.process(file);
    }
}
