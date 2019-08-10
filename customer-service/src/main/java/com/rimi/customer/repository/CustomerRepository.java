package com.rimi.customer.repository;

import com.rimi.customer.pojo.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author szf
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
}
