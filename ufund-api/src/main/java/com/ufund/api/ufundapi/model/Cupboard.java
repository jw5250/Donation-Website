package com.ufund.api.ufundapi.model;

import com.ufund.api.ufundapi.controller.NeedsController;

public class Cupboard {
    //private static final Logger LOG = Logger.getLogger(Cupboard.class.getName());
    private NeedsController needsController;
    public Cupboard(NeedsController needsController) {
        this.needsController = needsController;
    }

    /**
     * Creates a {@linkplain Need need} with the provided need object
     * 
     * @param need - The {@link Need need} to create
     * 
     * @return ResponseEntity with created {@link Need need} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Need need} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    
}

