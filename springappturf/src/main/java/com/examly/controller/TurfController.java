package com.examly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping
    public Turf addTurf(@RequestBody Turf t){
        service.addTurf(t);
        return t;
    }

    @PutMapping("/turfId")
    public Turf editTurf(@RequestBody Turf t){
        service.editTurf(t);
        return t;
    }

    @GetMapping
    public List<Turf> getTurfs(Turf t){
       return service.getTurfs();
    }

    @DeleteMapping("/{turfId}")
    public String deleteTurf(int turfId){
        service.deleteTurf(turfId);
        return "deleted";
    }


}
