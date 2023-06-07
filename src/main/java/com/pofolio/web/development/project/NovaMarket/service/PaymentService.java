package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.requestModels.PaymentInfoRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface PaymentService {
    public PaymentIntent createPaymentIntent(PaymentInfoRequest paymentInfoRequest) throws StripeException;
}
