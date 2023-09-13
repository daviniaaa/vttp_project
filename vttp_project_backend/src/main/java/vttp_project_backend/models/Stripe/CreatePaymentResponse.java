package vttp_project_backend.models.Stripe;

public class CreatePaymentResponse {
    private String clientSecret;

    

    public CreatePaymentResponse(String clientSecret) {
      this.clientSecret = clientSecret;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
