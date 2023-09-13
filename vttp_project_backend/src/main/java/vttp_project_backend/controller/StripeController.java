package vttp_project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import vttp_project_backend.models.Stripe.CreatePaymentResponse;
import vttp_project_backend.service.StripeService;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {
    @Autowired StripeService stripeSvc;
    
    @PostMapping(path = "/create-payment-intent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPaymentIntent() throws StripeException {

        System.out.println("postMapping called!! createPaymentIntent()");

        return ResponseEntity.ok(stripeSvc.createPaymentIntent());
    }

    @GetMapping(path = "/checkout")
    public ResponseEntity<String> stripeCheckout() {
        return ResponseEntity.ok("");
    }

}
