package com.rimi.search.controller;

import com.rimi.common.dto.RespDTO;
import com.rimi.common.exception.CommonException;
import com.rimi.common.exception.ErrorCode;
import com.rimi.search.client.CustomerServiceClient;
import com.rimi.search.pojo.Customer;
import com.rimi.search.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shangzf
 */
@Api(description = "查询相关接口")
@RestController
public class SearchController {

    @Autowired
    private ICustomerService studentService;
    @Autowired
    private CustomerServiceClient serviceClient;

    @ApiOperation(value = "根据条件查询学生信息", notes = "根据条件查询学生信息")
    @ApiImplicitParam(paramType = "path", value = "查询的条件", name = "searchTerm")
    @GetMapping(value = "/customers/{searchTerm}", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO findCustomerList(@PathVariable String searchTerm) {
        List<Customer> list = studentService.findByParam(searchTerm);
        return RespDTO.onSuc(list);
    }


    @ApiOperation(value = "同步学生信息", notes = "同步学生信息")
    @GetMapping(value = "/customers/sync", headers = "Authorization")
    @PreAuthorize("isAuthenticated()")
    public RespDTO syncCustomer() {
        List<Customer> allCustomer = serviceClient.getAll();
        if (allCustomer == null) {
            throw new CommonException(ErrorCode.FAIL);
        }
        studentService.saveStudentForList(allCustomer);
        return new RespDTO();
    }

}
