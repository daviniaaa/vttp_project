package vttp_project_backend.models.Stripe;

import com.google.gson.annotations.SerializedName;

public class CreatePaymentItem {
    @SerializedName("id")
    String id;

    public String getId() {
      return id;
    }
}
