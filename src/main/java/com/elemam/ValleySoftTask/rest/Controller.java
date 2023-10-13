package com.elemam.ValleySoftTask.rest;

import com.elemam.ValleySoftTask.Tafqet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/Tafqet")
    public String getTafqet(@RequestParam String number)
    {
        System.out.println(number);
        return Tafqet.tafqet(number);
    }
}
