package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Payment;
import com.pofolio.web.development.project.NovaMarket.repository.PaymentRepository;
import com.pofolio.web.development.project.NovaMarket.requestModels.PaymentInfoRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Resource
    private PaymentRepository paymentRepository;

    @Autowired
    UserService userService;

    @Autowired
    public PaymentServiceImpl(@Value("${stripe.key.secret}") String secretKey) {
        Stripe.apiKey = secretKey;
    }

    public PaymentIntent createPaymentIntent(PaymentInfoRequest paymentInfoRequest) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfoRequest.getAmount());
        params.put("currency", paymentInfoRequest.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);

        return PaymentIntent.create(params);
    }

    public ResponseEntity<String> stripePayment(String userEmail) throws Exception {

        Long customerId = userService.findUserByEmail(userEmail).get().getId();
        Optional<Payment> paymentOpt = paymentRepository.findByUserId(customerId);
        Payment payment = new Payment();
        if(paymentOpt.isEmpty()){
            throw new Exception("Payment information is missing");
        }
        payment.setAmount(0.0);
        paymentRepository.save(payment);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
