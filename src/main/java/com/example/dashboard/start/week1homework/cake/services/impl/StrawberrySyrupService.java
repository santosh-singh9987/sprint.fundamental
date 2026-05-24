package com.example.dashboard.start.week1homework.cake.services.impl;

import com.example.dashboard.start.week1homework.cake.services.SyrupService;
import org.springframework.stereotype.Service;

//@Component
//@Qualifier("strawberrysyrup")
@Service("strawberrySyrupService")
public class StrawberrySyrupService implements SyrupService {

    @Override
    public String getSyrupType() {
        return "Strawberry Syrup";
    }
}
