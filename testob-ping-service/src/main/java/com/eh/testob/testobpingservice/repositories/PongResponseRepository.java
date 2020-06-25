package com.eh.testob.testobpingservice.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eh.testob.testobpingservice.entities.PongResponse;

@Repository
public interface PongResponseRepository extends CrudRepository<PongResponse, String> {

   @Transactional
   List<PongResponse> findTop10ByOrderByIdDesc();


}
