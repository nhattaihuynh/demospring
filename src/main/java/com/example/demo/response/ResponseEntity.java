package com.example.demo.response;

import com.example.demo.Utils.GSonUtils;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseEntity {
    private String code;
    private String message;
    private Object data = new ArrayList<>();

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
