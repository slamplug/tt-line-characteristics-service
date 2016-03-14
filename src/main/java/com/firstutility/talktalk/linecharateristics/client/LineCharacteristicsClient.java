package com.firstutility.talktalk.linecharateristics.client;


import com.firstutility.talktalk.linecharateristics.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class LineCharacteristicsClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(LineCharacteristicsClient.class);

    @Value("${LineCharacteristicsWS.soapAction}")
    private String soapAction;

    @Value("${LineCharacteristicsWS.serviceEndpoint}")
    private String serviceEndpoint;

    @Value("${LineCharacteristicsWS.username}")
    private String username;

    @Value("${LineCharacteristicsWS.password}")
    private String password;

    @Value("${LineCharacteristicsWS.agentId}")
    private int agentId;

    public GetLineCharacteristicsResponse getLineCharacteristics(final String telephoneNumber, final String postCode) {

        log.info("getLineCharacteristics: telephone: {}, postCode: {}", telephoneNumber, postCode);

        Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        credentials.setAgentID(agentId);

        TelephonePostcodeRequest telephonePostcodeRequest = new TelephonePostcodeRequest();
        telephonePostcodeRequest.setTelephoneNumber(telephoneNumber);
        telephonePostcodeRequest.setPostcode(postCode);
        GetLineCharacteristics getLineCharacteristics = new GetLineCharacteristics();

        GetLineCharacteristicsRequest getLineCharacteristicsRequest = new GetLineCharacteristicsRequest();
        getLineCharacteristicsRequest.setUserCredentials(credentials);
        getLineCharacteristicsRequest.setUserConsent(UserConsentEnum.YES);
        getLineCharacteristicsRequest.setServiceType(ServiceTypeEnum.MPF);
        getLineCharacteristicsRequest.setRequestDetails(telephonePostcodeRequest);

        getLineCharacteristics.setRequest(getLineCharacteristicsRequest);

        GetLineCharacteristicsResponse result =
                (GetLineCharacteristicsResponse) getWebServiceTemplate()
                        .marshalSendAndReceive(serviceEndpoint, getLineCharacteristics, new SoapActionCallback(soapAction));

        return result;
    }
}
