package com.atillatpc.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Eskisi
/**
 * ModelMapper nesnesini Spring Context'e bean olarak tanımlar.
 * Bean ismi: "modelMapper"
 * initMethod: Bean oluşturulduktan hemen sonra çalışır
 * destroyMethod: Uygulama kapanırken çalışır
 */
//@Bean(name = "modelMapper", initMethod = "onInit", destroyMethod = "onDestroy")

@Configuration
public class ModelMapperBean extends BeanAllMethod {

    // Instance
    private final ModelMapper modelMapper = new ModelMapper();

    // Bean
    @Bean(name = "modelMapper")
    public ModelMapper modelMapperMethod() {
        //return new ModelMapper(); // 1.YOL
        return modelMapper;         // 2.YOL
    }

    /**
     * Bean oluşturulduğunda çalışacak metod
     */
    @PostConstruct
    @Override
    public void onInit() {
        System.out.println("✅ ModelMapper başladı (initialized)");
    }

    /**
     * Bean yok edilmeden önce çalışacak metod
     */
    @PreDestroy
    @Override
    public void onDestroy() {
        System.out.println("🧹 ModelMapper bean öldü(destroyed)");
    }
} //end ModelMapperBean