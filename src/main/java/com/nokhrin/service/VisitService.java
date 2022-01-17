package com.nokhrin.service;

import com.nokhrin.domain.AppUser;
import com.nokhrin.domain.Doctor;
import com.nokhrin.domain.Visit;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface VisitService {
    void addVisit(Visit visit);
    void editVisit(Visit visit);
    List<Visit> listVisit();
    void removeVisit (long id);
    Visit getVisit(long id);
    List<Visit> findByClient_id(long id);
    List<Visit> getUnpaidVisits(boolean isPaid);
    List<Time> getAvailableHours(Visit visit);
    Visit getActiveVisitByLogin(String login);
    Visit getUpaidVisit(AppUser appUser );
    boolean hasUnpaidVisit(AppUser appUser);
    boolean hasActualVisit(AppUser appUser);
    List<Visit> getVisitsListForDoc(Doctor doctor);
}
