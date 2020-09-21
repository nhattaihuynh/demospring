package com.example.demo.response;

import com.example.demo.Utils.GSonUtils;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseEntity {
    private Integer code;
    private String message;
    private Object data = new ArrayList<>();
    private Integer totalItems = 0;

    public ResponseEntity() {
        this.code = HTTPStatus.SUCCESS.getCode();
        this.message = HTTPStatus.SUCCESS.getMessage();
    }
    
    @Override
    public String toString(){
        System.out.println(GSonUtils.getGSonInstance().toJson(this));
       return GSonUtils.getGSonInstance().toJson(this);
    }
}
