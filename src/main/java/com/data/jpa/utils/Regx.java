package com.data.jpa.utils;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Data
@Getter
@Component
public class Regx {
    private String emailRegx = "^[A-Za-z0-9+_.-]+@gmail\\.com$";
    private String passwordRegx = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{8,}$";
}
