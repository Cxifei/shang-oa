package com.rimi.search.service.impl;

import com.rimi.search.pojo.Customer;
import com.rimi.search.repository.CustomerRepository;
import com.rimi.search.service.ICustomerService;
import com.rimi.search.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findByParam(String param) {
        Page<Customer> studentPage = customerRepository.search(PageUtils.getSearchQuery(param));
        return studentPage.getContent();
    }

    @Override
    public List<Customer> findByParam(String param, int pageSize, int pageNum) {
        Page<Customer> students = customerRepository.search(PageUtils.getSearchQueryByPage(param, pageSize, pageNum));
        return students.getContent();
    }

    @Override
    public void saveStudentForList(List<Customer> customerList){
        customerRepository.saveAll(customerList);
    }
}
