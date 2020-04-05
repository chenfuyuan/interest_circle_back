package com.cfy.interestback.vo;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AjaxMessage {
    private boolean success;
    private String Message;
}
