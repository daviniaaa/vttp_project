package vttp_project_backend.models.Stripe;

import com.google.gson.annotations.SerializedName;

public class CreatePayment {
    @SerializedName("items")
    // CreatePaymentItem[] items;

    // public CreatePaymentItem[] getItems() {
    //   return items;
    // }

    Object[] items;

    public Object[] getItems() {
        return items;
    }
    
}
