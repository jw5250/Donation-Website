import { Need } from './need';
export interface User {
    name: string;
    isManager: boolean;
    totalDonations : number;
    fundingBasket: Need[];
    availableRewards: string[];
}