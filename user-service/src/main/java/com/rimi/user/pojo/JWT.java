package com.rimi.user.pojo;

import lombok.Data;

/**
 * @author shangzf
 */
@Data
public class JWT {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private String jti;

}
