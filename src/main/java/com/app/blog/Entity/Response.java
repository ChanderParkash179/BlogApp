package com.app.blog.Entity;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String responseCode;
    private String responseMessage;
    private Map<String,Object> responseData;
}
