package com.pofolio.web.development.project.NovaMarket.requestModels;

import lombok.Data;

@Data
public class PaymentInfoRequest {

    private double amount;
    private String currency;
    private String receiptEmail;

}
