package com.nokhrin.dao;

import com.nokhrin.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface VisitRepository   extends JpaRepository<Visit, Long> {
    Visit findById(long id);
    List<Visit> findByClient_id(long id);
    List<Visit> findByIsPaid(boolean isPaid);
}
