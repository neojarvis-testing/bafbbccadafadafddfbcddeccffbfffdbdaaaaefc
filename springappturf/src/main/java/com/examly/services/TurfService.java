package com.examly.services;

import java.util.List;

import com.examly.model.Turf;

public interface TurfService {

    public List<Turf> getTurfs();
    public void addTurf(Turf t);
    public void editTurf(Turf t);
    public Turf getTurfById(int turfId);
    public void deleteTurf(int turfId);

}
