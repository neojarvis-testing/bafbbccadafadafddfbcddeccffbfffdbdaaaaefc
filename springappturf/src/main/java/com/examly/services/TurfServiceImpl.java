package com.examly.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.model.Turf;
import com.examly.repository.TurfRepo;

@Service
public class TurfServiceImpl implements TurfService{
    @Autowired
    TurfRepo repo;
     public List<Turf> getTurfs(){
        return repo.findAll();

     }
    public void addTurf(Turf t){
        repo.save(t);

    }
    public void editTurf(Turf t){
        repo.save(t);

    }
    // public Turf getTurfById(int turfId){
    //     if(repo.existsById(turfId)){
    //         Optional<Turf> o = repo.findById(turfId);
    //         Turf t = o.get();
    //     }
    
    // }

    public void deleteTurf(int turfId){
        repo.deleteById(turfId);
    }


}
