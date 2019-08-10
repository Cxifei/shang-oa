package com.rimi.consultant.service.impl;

import com.rimi.common.service.impl.MongoBaseServiceImpl;
import com.rimi.consultant.pojo.Consultant;
import com.rimi.consultant.repository.ConsultantRepository;
import com.rimi.consultant.service.IConsultantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConsultantServiceImpl extends MongoBaseServiceImpl<ConsultantRepository, Consultant, String> implements IConsultantService {
    @Override
    public Consultant save(Consultant consultant) {
        super.insertSelective(consultant);
        return consultant;
    }

    @Override
    public Consultant update(Consultant consultant) {
        super.updateSelectiveById(consultant);
        return consultant;
    }

    @Override
    public Page<Consultant> selectByParamForPage(Pageable pageable) {
        return super.selectPage(pageable);
    }
}


