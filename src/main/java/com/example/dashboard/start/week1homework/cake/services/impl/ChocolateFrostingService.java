package com.example.dashboard.start.week1homework.cake.services.impl;

import com.example.dashboard.start.week1homework.cake.services.FrostingService;
import org.springframework.stereotype.Service;

//@Component
//@Qualifier("chocolatefrosting")

@Service("chocolateFrostingService")
public class ChocolateFrostingService implements FrostingService {

    @Override
    public String getFrostingType() {
        return "Chocolate Frosting";
    }
}
