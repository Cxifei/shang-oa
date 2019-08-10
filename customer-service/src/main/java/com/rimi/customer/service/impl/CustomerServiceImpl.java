package com.rimi.customer.service.impl;

import com.rimi.common.service.impl.MongoBaseServiceImpl;
import com.rimi.customer.api.vo.CustomerInfo;
import com.rimi.customer.pojo.Customer;
import com.rimi.customer.repository.CustomerRepository;
import com.rimi.customer.service.ICustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author shangzf
 */
@Service
public class CustomerServiceImpl extends MongoBaseServiceImpl<CustomerRepository, Customer, String> implements ICustomerService {
    @Override
    public Page<Customer> getCustomerByPage(int pageNum, int pageSize) {
        // 设置排序规则
        Sort sort = Sort.by(Sort.Direction.DESC, "updTime");
        return super.selectPage(PageRequest.of(pageNum, pageSize, sort));
    }

    @Override
    public String saveCustomer(CustomerInfo info) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(info, customer);
        // 判断手机号码是否重复,如果手机号码不存在，则返回false
        Boolean b = checkTelephone(customer.getTelephone());
        // 当检查手机号码返回的结果为null或者为true时，不能保存
        if (b == null || b) {
            return null;
        }
        super.insertSelective(customer);
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerInfo info) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(info, customer);
        super.updateSelectiveById(customer);
    }

    @Override
    public CustomerInfo findById(String id) {
        Customer customer = super.selectById(id);
        CustomerInfo info = new CustomerInfo();
        BeanUtils.copyProperties(customer, info);
        return info;
    }

    @Override
    public Boolean checkTelephone(String telephone) {
        // 如果手机号码为空，则直接返回false
        if (StringUtils.isNotBlank(telephone)) {
            Customer customer = new Customer();
            customer.setTelephone(telephone);
            return super.selectOne(customer) != null;
        }
        return null;

    }
}
