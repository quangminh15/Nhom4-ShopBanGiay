package com.fpoly.ShopBanGiay.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class VerificationCode {
	private String code;
    private LocalDateTime createdTime;
    
	public VerificationCode(String code) {
        this.code = code;
        this.createdTime = LocalDateTime.now();
    }
}
