package com.example.dashboard.start.week1homework.cake.services.impl;

import com.example.dashboard.start.week1homework.cake.services.SyrupService;
import org.springframework.stereotype.Service;

//@Component
//@Qualifier("chocolatesyrup")

@Service("chocolateSyrupService")
public class ChocolateSyrupService implements SyrupService {

    @Override
    public String getSyrupType() {
        return "Chocolate Syrup";
    }
}

