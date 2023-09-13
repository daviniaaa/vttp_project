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


export interface Address {
  block: string;
  streetName: string;
  // floorNumber: string;
  // unitNumber: string;
  buildingName: string;
  postalCode: string;
}

export interface Location {
  latitude: string;
  longitude: string;
}

export interface TimePeriodObject {
  startDate: string;
  endDate: string;
  startTime: string;
  endTime: string;
  label: string;
}

export interface EventDetailListObject {
  timePeriod: TimePeriodObject[];
}

export interface Thumbnail {
  uuid: string;
}

export interface DataObject {
  uuid: string;
  name: string;
  type: string;
  tags: string[];
  description: string;
  body: string;
  location: Location;
  address: Address;
  eventDetailList: EventDetailListObject[];
  thumbnails: Thumbnail[];
}
