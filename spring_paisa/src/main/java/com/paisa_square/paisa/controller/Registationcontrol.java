package com.paisa_square.paisa.controller;

import com.paisa_square.paisa.model.Advertise;
import com.paisa_square.paisa.model.Visits;
import com.paisa_square.paisa.repository.Registerrepository;
import com.paisa_square.paisa.serice.Registerservice;
import com.paisa_square.paisa.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Registationcontrol {
    @Autowired
    private Registerservice service;
    @Autowired
    private Registerrepository registerRepo;
    @PostMapping("/registeruser")
    @CrossOrigin(origins = "http://localhost:4200/")
    public Register registerUser(@RequestBody Register user) throws Exception {
        String tempEmailId = user.getEmail();
        if(tempEmailId!=null && !"".equals(tempEmailId)){
            Register userobj=service.fetchUserByEmailId(tempEmailId);
            if(userobj!=null){
                throw new Exception("emailid is exist");
            }
        }
        Register userobj=null;
        userobj=service.saveUser(user);
        return userobj;
    }
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200/")
    public Register loginUser(@RequestBody Register login) throws Exception {
        String tempEmailId=login.getEmail();
        String tempPass=login.getPassword();
        System.out.println("sai");
        Register userObj=null;
        if(tempEmailId!=null && tempPass!=null){
            userObj=service.fetchUserByEmailIdAndPassword(tempEmailId,tempPass);
        }
        if(userObj==null){
            throw new Exception("Bad email and password"+tempEmailId+tempPass);
        }
        return userObj;
    }

    @GetMapping("/{userid}/profile")
    @CrossOrigin(origins = "http://localhost:4200")
    public Optional<Register> getAllAdvertisements(@PathVariable("userid") Long userid) {
        System.out.println("profile obj==++>>"+registerRepo.findById(userid));
        return registerRepo.findById(userid);
    }
    @PostMapping("{userid}/updateProfile")
    @CrossOrigin(origins = "http://localhost:4200/")
    public Register visit(@RequestBody Register profile, @PathVariable("userid") Long userid) throws Exception {
        Optional<Register> userProfile = registerRepo.findById(userid);
        Register userprofileobj = null;
        System.out.println("profile from angular cd"+profile);
        if (userProfile.isPresent()) {
            userprofileobj = userProfile.get();
            userprofileobj.setBrandname(profile.getBrandname());
            userprofileobj.setFirstname(profile.getFirstname());
            userprofileobj.setLastname(profile.getLastname());
            userprofileobj.setEmail(profile.getEmail());
            userprofileobj.setMobilenumber(profile.getMobilenumber());
            userprofileobj.setCountry(profile.getCountry());
            userprofileobj.setCountrycode(profile.getCountrycode());
            userprofileobj.setAddress(profile.getAddress());
            userprofileobj.setWebsite(profile.getWebsite());
            userprofileobj.setYoutube(profile.getYoutube());
            userprofileobj.setFacebook(profile.getFacebook());
            userprofileobj.setInstagram(profile.getInstagram());
            userprofileobj.setTwitter(profile.getTwitter());
            userprofileobj.setPinterest(profile.getPinterest());
            userprofileobj.setBio(profile.getBio());
            registerRepo.save(userprofileobj);
        }
        System.out.println("user profile obj after update"+userprofileobj);
        return userprofileobj;
    }
}
