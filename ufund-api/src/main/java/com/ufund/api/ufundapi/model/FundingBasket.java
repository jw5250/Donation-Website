package com.ufund.api.ufundapi.model;

import com.ufund.api.ufundapi.controller.FundingBasketController;

public class FundingBasket {
    //private static final Logger LOG = Logger.getLogger(Cupboard.class.getName());
    private FundingBasketController fundingBasketController;
    public FundingBasket(FundingBasketController fundingBasketController) {
        this.fundingBasketController = fundingBasketController;
    }
}

