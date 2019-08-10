package com.rimi.customer.repository;

import com.rimi.customer.pojo.CallbackRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallbackRecordRepository extends MongoRepository<CallbackRecord, String> {
}
