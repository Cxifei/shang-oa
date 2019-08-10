package com.rimi.consultant.repository;

import com.rimi.consultant.pojo.Consultant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultantRepository extends MongoRepository<Consultant, String> {

}
