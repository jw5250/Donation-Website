import { Need } from './need';
export interface User {
    name: string;
    isManager: boolean;
    fundingBasket: Need[];
  }