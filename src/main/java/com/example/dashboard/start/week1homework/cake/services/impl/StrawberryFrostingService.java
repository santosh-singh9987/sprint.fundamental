package com.example.dashboard.start.week1homework.cake.services.impl;

import com.example.dashboard.start.week1homework.cake.services.FrostingService;
import org.springframework.stereotype.Service;

//@Component
//@Qualifier("strawberryfrosting")
@Service("strawberryFrostingService")
public class StrawberryFrostingService implements FrostingService {

    @Override
    public String getFrostingType() {
        return "Strawberry Frosting";
    }
}