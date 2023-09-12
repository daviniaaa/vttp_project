import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

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
import { ExternalComponent } from './components/external/external.component';

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
    ExternalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    FormsModule, ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
