package com.rimi.user.dto;

import com.rimi.user.pojo.User;
import lombok.Data;

/**
 * @author shangzf
 */
@Data
public class LoginDTO {
    private User user;
    private String token;
}
