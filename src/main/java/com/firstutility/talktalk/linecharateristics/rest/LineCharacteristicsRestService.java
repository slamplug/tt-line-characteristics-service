package com.firstutility.talktalk.linecharateristics.rest;

import com.firstutility.talktalk.linecharateristics.GetLineCharacteristicsResponse;
import com.firstutility.talktalk.linecharateristics.client.LineCharacteristicsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineCharacteristicsRestService {

    @Autowired
    LineCharacteristicsClient lineCharacteristicsClient;

    private static final Logger log = LoggerFactory.getLogger(LineCharacteristicsRestService.class);

    @RequestMapping(method = RequestMethod.GET, path = "/linecharacteristics", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetLineCharacteristicsResponse getLineCharacteristicsResponse(
            @RequestParam(name = "telephoneNumber") String telephoneNumber,
            @RequestParam(name = "postCode") String postCode) {

        log.info("GetLineCharacteristics: telephone: {}, postCode: {}", telephoneNumber, postCode);

        return lineCharacteristicsClient.getLineCharacteristics(telephoneNumber, postCode);
    }
}
