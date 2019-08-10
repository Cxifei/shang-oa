package com.rimi.customer.api;

import com.rimi.customer.api.vo.CustomerInfo;
import com.rimi.customer.pojo.Customer;
import com.rimi.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shangzf
 */
@RestController
@RequestMapping("/api")
public class ApiCustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping(value = "/customer/{id}")
    public CustomerInfo getCustomerById(@PathVariable String id) {
        return customerService.findById(id);
    }

    @PostMapping("/customer")
    public String saveCustomer(@RequestBody CustomerInfo info) {
        return customerService.saveCustomer(info);
    }

    @PutMapping("/customer")
    public void updateCustomer(@RequestBody CustomerInfo info) {
        customerService.updateCustomer(info);
    }

    @GetMapping("/customers")
    public List<Customer> getAll() {
        return customerService.selectListAll();
    }
}
