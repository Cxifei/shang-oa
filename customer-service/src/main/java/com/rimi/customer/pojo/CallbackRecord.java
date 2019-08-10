package com.rimi.customer.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "oa_callback_record")
public class CallbackRecord implements Serializable {

    @Id
    private String id;

    /**
     * 学生ID
     */
    private String student;
    /**
     * 回访记录
     */
    private String callbackRecord;

    private Date crtTime;
    private String crtUser;
    private String crtName;
    private String crtHost;
}
