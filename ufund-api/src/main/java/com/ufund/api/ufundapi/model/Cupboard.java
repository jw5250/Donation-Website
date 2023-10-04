package com.ufund.api.ufundapi.model;

import com.ufund.api.ufundapi.controller.NeedsController;

public class Cupboard {
    //private static final Logger LOG = Logger.getLogger(Cupboard.class.getName());
    private NeedsController needsController;
    public Cupboard(NeedsController needsController) {
        this.needsController = needsController;
    }
}