package com.rimi.consultant.service;

import com.rimi.common.service.IBaseService;
import com.rimi.consultant.pojo.Consultant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IConsultantService extends IBaseService<Consultant,String> {

    Consultant save(Consultant consultant);

    Consultant update(Consultant consultant);

    Page<Consultant> selectByParamForPage(Pageable pageable);

//    void deleteById(String id);
//
//    List<NetworkConsultation> findAll();
//
//    NetworkConsultation findById(String id);
}
