package com.rimi.customer.service.impl;

import com.rimi.common.service.impl.MongoBaseServiceImpl;
import com.rimi.customer.pojo.CallbackRecord;
import com.rimi.customer.repository.CallbackRecordRepository;
import com.rimi.customer.service.ICallbackRecordService;
import org.springframework.stereotype.Service;

@Service
public class CallbackRecordServiceImpl extends MongoBaseServiceImpl<CallbackRecordRepository, CallbackRecord,String> implements ICallbackRecordService {
}
