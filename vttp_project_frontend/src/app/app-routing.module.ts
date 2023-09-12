import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { CreateaccountComponent } from './components/createaccount/createaccount.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { SearchComponent } from './components/search/search.component';
import { EventComponent } from './components/event/event.component';
import { CreateboothComponent } from './components/createbooth/createbooth.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SettingsComponent } from './components/profile/settings/settings.component';
import { ExternalComponent } from './components/external/external.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "login", component: LoginComponent},
  {path: "createaccount", component: CreateaccountComponent},
  {path: "events/:eventId", component: EventComponent},
  {path: "external/:eventId", component: ExternalComponent},
  {path: "createbooth/:eventId", component: CreateboothComponent},
  {path: "search", component: SearchComponent},
  {path: "profile/:profileId", component: ProfileComponent},
  {path: "settings", component: SettingsComponent},
  {path: "**", component: NotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
