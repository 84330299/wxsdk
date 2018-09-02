package org.chen.dto;

import lombok.Data;

import java.util.Map;

/**
 * Author:Mr.Chen
 * Date:2018/8/7
 */
@Data
public class TemplateMsgOrder {

    private Map<String, Object> Time;
    private Map<String, Object> Productname;
    private Map<String, Object> Total;
    private Map<String, Object> Buytime;

}
