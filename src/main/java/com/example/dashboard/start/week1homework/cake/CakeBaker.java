package com.example.dashboard.start.week1homework.cake;

import com.example.dashboard.start.week1homework.cake.services.FrostingService;
import com.example.dashboard.start.week1homework.cake.services.SyrupService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CakeBaker  implements CommandLineRunner {

    private final FrostingService frosting;
    private final SyrupService syrup;

//    @Autowired
    public CakeBaker(
            @Qualifier("chocolateFrostingService") FrostingService frosting,
            @Qualifier("strawberrySyrupService") SyrupService syrup
    ) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public static void main(String[] args) {
        SpringApplication.run(CakeBaker.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.bakeCake();
    }

    public void bakeCake() {
//        System.out.println("Baking Cake");
//        System.out.println("syrup: " + syrup.getSyrupType());
//        System.out.println("frosting: " + frosting.getFrostingType());
    }
}
