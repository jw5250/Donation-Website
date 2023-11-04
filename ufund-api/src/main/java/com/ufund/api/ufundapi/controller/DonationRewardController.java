package com.ufund.api.ufundapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;
import com.ufund.api.ufundapi.controller.controllerInterface;
import com.ufund.api.ufundapi.model.DonationReward;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.persistence.DataFileDAO;

@RestController
@RequestMapping("donationReward")
public class DonationRewardController extends controllerInterface<DonationReward> {
        /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param DonorDao The {@link UserDAO User Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public DonationRewardController(DataFileDAO<DonationReward> DonorDao) {
        super(DonorDao, Logger.getLogger(DonationRewardController.class.getName()));
    }
}
