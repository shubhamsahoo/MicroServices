package com.techouts.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServiceConfig.class, PersistanceConfig.class})
public class RootAppConfig {

}
