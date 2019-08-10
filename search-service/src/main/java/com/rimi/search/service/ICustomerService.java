package com.rimi.search.service;


import com.rimi.search.pojo.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> findByParam(String param);

    List<Customer> findByParam(String param, int pageSize, int pageNum);

    void saveStudentForList(List<Customer> customerList);
}
