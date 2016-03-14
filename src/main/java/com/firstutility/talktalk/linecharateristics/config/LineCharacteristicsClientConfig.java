package com.firstutility.talktalk.linecharateristics.config;

import com.firstutility.talktalk.linecharateristics.client.LineCharacteristicsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class LineCharacteristicsClientConfig {

    @Bean
    public Jaxb2Marshaller lineCharacteristicsMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.firstutility.talktalk.linecharateristics");
        return marshaller;
    }

    @Bean
    public LineCharacteristicsClient lineCharacteristicsClient(Jaxb2Marshaller lineCharacteristicsMarshaller) {
        LineCharacteristicsClient client = new LineCharacteristicsClient();
        client.setDefaultUri("http://webservices.opalonline.co.uk/LineCharacteristicsWS");
        client.setMarshaller(lineCharacteristicsMarshaller);
        client.setUnmarshaller(lineCharacteristicsMarshaller);
        return client;
    }
}
