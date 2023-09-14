import { NgModule, isDevMode } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { StripeModule } from 'stripe-angular';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { CreateaccountComponent } from './components/createaccount/createaccount.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppMaterialModule } from './app-material/app-material.module';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { ErrordialogComponent } from './components/errordialog/errordialog.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SearchComponent } from './components/search/search.component';
import { EventComponent } from './components/event/event.component';
import { BoothComponent } from './components/event/booth/booth.component';
import { CreateboothComponent } from './components/createbooth/createbooth.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SettingsComponent } from './components/profile/settings/settings.component';
import { AccountComponent } from './components/profile/settings/account/account.component';
import { AdvancedComponent } from './components/profile/settings/advanced/advanced.component';
import { NotificationsComponent } from './components/profile/settings/notifications/notifications.component';
import { CheckoutComponent } from './components/stripe/checkout/checkout.component';
import { CheckoutsuccessComponent } from './components/stripe/checkoutsuccess/checkoutsuccess.component';
import { MapComponent } from './components/map/map.component';
import { DeletedialogComponent } from './components/deletedialog/deletedialog.component';
import { ServiceWorkerModule } from '@angular/service-worker';
import { AboutmeComponent } from './components/aboutme/aboutme.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    CreateaccountComponent,
    NotfoundComponent,
    ErrordialogComponent,
    NavbarComponent,
    SearchComponent,
    EventComponent,
    BoothComponent,
    CreateboothComponent,
    ProfileComponent,
    SettingsComponent,
    AccountComponent,
    AdvancedComponent,
    NotificationsComponent,
    CheckoutComponent,
    CheckoutsuccessComponent,
    MapComponent,
    DeletedialogComponent,
    AboutmeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    FormsModule, ReactiveFormsModule,
    HttpClientModule,
    StripeModule,
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: !isDevMode(),
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
