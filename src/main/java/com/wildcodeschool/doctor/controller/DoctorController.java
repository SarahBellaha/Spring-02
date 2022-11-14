package com.wildcodeschool.doctor.controller;

import com.wildcodeschool.doctor.model.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class DoctorController {

    @GetMapping("/doctor/{number}")
    @ResponseBody
    public Doctor doctor(@PathVariable int number, @RequestParam(defaultValue = "", required = false) String name) {
        if(number == 13) {
            String[] removeHyphen = !name.isEmpty() ? name.split("-") : new String[]{""};
            String drName = String.join(" ", removeHyphen);

            return new Doctor(number, drName);
        }
        if(number >= 1 && number <= 12) {
            throw new ResponseStatusException(HttpStatus.SEE_OTHER);
        }
        if(number > 12) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible de récupérer le Doctor n° " + number);
        }

        return new Doctor();
    }
}
