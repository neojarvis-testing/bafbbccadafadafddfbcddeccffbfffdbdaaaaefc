package com.examly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.model.Turf;
import com.examly.services.TurfServiceImpl;

@RestController
@RequestMapping("/turf")
public class TurfController {

    @Autowired
    TurfServiceImpl service;

    public Turf addTurf(@RequestBody Turf t){
        service.addTurf(t);
        return t;
    }

    public Turf editTurf(@RequestBody Turf t){
        service.editTurf(t);
        return t;
    }

    public List<Turf> getTurfs(Turf t){
        service.getTurfs();
        
    }


}
