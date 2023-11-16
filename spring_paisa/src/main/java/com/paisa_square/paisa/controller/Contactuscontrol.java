package com.paisa_square.paisa.controller;

import com.paisa_square.paisa.model.Contactus;
import com.paisa_square.paisa.serice.Contactusservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Contactuscontrol {
    @Autowired
    private Contactusservice service;
    @PostMapping("/contactus")
    @CrossOrigin(origins = "http://localhost:4200/")
    public Contactus contact(@RequestBody Contactus issue) throws Exception {
        Contactus saveissueobj=null;
        saveissueobj=service.saveissue(issue);
        System.out.println(saveissueobj);
        if(issue==null){
            throw new Exception("Bad contactus details");
        }
        return saveissueobj;
    }
}
