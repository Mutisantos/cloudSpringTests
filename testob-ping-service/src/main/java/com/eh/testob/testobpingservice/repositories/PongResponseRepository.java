package com.eh.testob.testobpingservice.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eh.testob.testobpingservice.entities.PongResponse;

@Repository
public interface PongResponseRepository extends JpaRepository<PongResponse, String> {

   @Transactional
   List<PongResponse> findTop10OrderByResponseTimestamp();


}
