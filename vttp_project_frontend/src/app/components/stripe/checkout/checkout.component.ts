import { Stripe, StripeElements, StripePaymentElement, StripePaymentElementOptions, loadStripe } from '@stripe/stripe-js';
import { Component, OnInit } from '@angular/core';




@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})

export class CheckoutComponent implements OnInit {

  // constructor(private stripeImport: Stripe) {}
  // stripe = Stripe("pk_test_51NpmaoF9s0tz26OGgxnSu9r9zV4AXsbx0eyA7IoX86ge83rNSd04BZuT0NUjTs7YNwIrEaE5geI4sOKinf7rt0ML00hZIdcnpC");
  stripe: any;
  elements!: StripeElements;

  ngOnInit() {
    this.initialize();


  }


  async initialize() {
    loadStripe("pk_test_51NpmaoF9s0tz26OGgxnSu9r9zV4AXsbx0eyA7IoX86ge83rNSd04BZuT0NUjTs7YNwIrEaE5geI4sOKinf7rt0ML00hZIdcnpC")
      .then(data => {
        this.stripe = data as Stripe;
      })



    const response = await fetch("/api/stripe/create-payment-intent", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
    });
    const { clientSecret } = await response.json();
    console.log(clientSecret);

    const appearance = {
      theme: 'stripe',
      variables: {
        colorPrimary: '#c29bfd',
      },
    };

    this.elements = await this.stripe.elements({ clientSecret });
    // this.elements = await { appearance, clientSecret };
    // this.elements = (window as any).stripe.elements({ appearance });

    const paymentElementOptions: StripePaymentElementOptions = {
      layout: "tabs",
    };

    // const paymentElement = this.elements.create("payment", paymentElementOptions);
    const paymentElement: StripePaymentElement = this.elements.create("payment", paymentElementOptions);
    paymentElement.mount("#payment-element");
    console.log("mounted paymentElement")
  }


  async handleSubmit(e: any) {
    e.preventDefault();
    this.setLoading(true);

    const { error } = await this.stripe.confirmPayment({
      elements: this.elements,
      confirmParams: {
        // Make sure to change this to your payment completion page
        // return_url: "http://localhost:4200/#/checkoutsuccess",
        return_url: "https://davinia.konbini.dev/#/checkoutsuccess",
      },
    });

    // This point will only be reached if there is an immediate error when
    // confirming the payment. Otherwise, your customer will be redirected to
    // your `return_url`. For some payment methods like iDEAL, your customer will
    // be redirected to an intermediate site first to authorize the payment, then
    // redirected to the `return_url`.
    if (error.type === "card_error" || error.type === "validation_error") {
      this.showMessage(error.message || "");
    } else {
      this.showMessage("An unexpected error occurred.");
    }

    this.setLoading(false);
  }

  // ------- UI helpers -------

  showMessage(messageText: string) {
    const messageContainer = document.querySelector("#payment-message");

    messageContainer!.classList.remove("hidden");
    messageContainer!.textContent = messageText;

    setTimeout(function () {
      messageContainer!.classList.add("hidden");
      messageContainer!.textContent = "";
    }, 4000);
  }

  setLoading(isLoading: boolean) {
    if (isLoading) {
      // Disable the button and show a spinner
      (document.querySelector("#submit")! as any).disabled = true;
      document.querySelector("#spinner")!.classList.remove("hidden");
      document.querySelector("#button-text")!.classList.add("hidden");
    } else {
      (document.querySelector("#submit")! as any).disabled = false;
      document.querySelector("#spinner")!.classList.add("hidden");
      document.querySelector("#button-text")!.classList.remove("hidden");
    }
  }
}
