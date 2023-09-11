export interface UserData {
  userDataId?: string;
  email: string;
  displayName?: string;
  userPassword: string;
  imageUrl?: string;
  confirmPassword?: string;
  token?: string;
}

export interface EventDetails {
    eventDetailsId: string;
    userDataId: string;
    title: string;
    description: string;
    imageUrl: string;
    category: string;
}

export interface BoothDetails {
  boothId?: string;
  userDataId?: string;
  eventDetailsId?: string;
  boothName: string;
  description: string;
}
