package com.nokhrin.service;

import com.nokhrin.dao.VisitRepository;
import com.nokhrin.domain.AppUser;
import com.nokhrin.domain.Doctor;
import com.nokhrin.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class VisitServiceImpl implements VisitService {

private VisitRepository visitRepository;

@Autowired
public VisitServiceImpl(VisitRepository visitRepository){
    this.visitRepository=visitRepository;
}
    @Transactional
    public void addVisit(Visit visit) {
        visitRepository.save(visit);
    }

    @Transactional
    public List<Visit> listVisit() {
        return visitRepository.findAll();
    }

    @Transactional
    public void removeVisit(long id) {
visitRepository.delete(id);
    }

    @Transactional
    public Visit getVisit(long id) {
        return visitRepository.findById(id);
    }

    @Transactional
    public List<Visit> findByClient_id(long id) {
        return visitRepository.findByClient_id(id);
    }
    @Transactional
    public void editVisit(Visit visit){
    visitRepository.save(visit);
    }
    @Transactional
    public  List<Visit> getUnpaidVisits(boolean isPaid){
    return visitRepository.findByIsPaid(isPaid);
    }
    @Transactional
    public List<Time> getAvailableHours(Visit visit){
        List<Time> visitsTime = new ArrayList<Time>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (Visit v: listVisit()) {
            if (v.getDoctor().getId() == visit.getDoctor().getId() && dateFormat.format(v.getDate()).equals(dateFormat.format(visit.getDate()))) {
                visitsTime.add(v.getTime());
            }
        }
        Time time= new Time(1);
        List<Time> avaliableTime = new ArrayList<Time>();
        for(long t = 36000000; t<64800000; t=t+1800000){
            time.setTime(t);
            if(!visitsTime.contains(time)){
                avaliableTime.add(new Time(t));
            }

        }
        return avaliableTime;
    }

    @Transactional
    public  Visit getActiveVisitByLogin(String login){
        Visit visit = new Visit();
        for (Visit v : listVisit()) {
            if (v.getClient().getLogin().equals(login)  && v.isActual()) {
                visit = v;
            }
        }
        return visit;
    }

    @Transactional
    public Visit getUpaidVisit(AppUser appUser ){
        Visit visit=new Visit();
        for (Visit v: findByClient_id(appUser.getId())) {
            if(!v.isPaid()){
                visit = v;
            }
        }
        return visit;
    }

    @Transactional
    public  boolean hasUnpaidVisit(AppUser appUser){
        boolean hasSomeVisit=false;
        for (Visit v: findByClient_id(appUser.getId())) {
            if(!v.isPaid()){
                hasSomeVisit = true;
            }
        }
        return hasSomeVisit;
    }

    @Transactional
    public  boolean hasActualVisit(AppUser appUser){
        boolean hasSomeVisit=false;
        for (Visit v: findByClient_id(appUser.getId())) {
            if(v.isActual()){
                hasSomeVisit = true;
            }
        }
        return hasSomeVisit;
    }

    @Transactional
    public  List<Visit> getVisitsListForDoc(Doctor doctor){
        List<Visit> visitList = new ArrayList<Visit>();
        //       System.out.println(doctor.getId());
        for (Visit visit: listVisit() ) {
            if(visit.getDoctor().getId()==doctor.getId() && visit.isActual()){
                visitList.add(visit);
            }

        }
        return  visitList;
    }
}
