# tt-line-characteristics-service

## Valid Response

http://localhost:8080/tt-linecharacteristics-service/linecharacteristics?telephoneNumber=01630656588&postCode=TF93DJ

```
{
   "getLineCharacteristicsResult":{
      "status":{
         "hasErrors":false,
         "errors":{
            "error":[

            ]
         }
      },
      "responseDetails":{
         "characteristicsBase":[
            {
               "exchangeDetails":{
                  "exchangeCode":"WNMD",
                  "exchangeName":"MARKET DRAYTON",
                  "exchangeState":"LIVE",
                  "exchangeCapacity":"G",
                  "productMessages":null,
                  "forecastDate":1270594800000,
                  "circuitLineLength":{
                     "measureType":"CircuitEstimated",
                     "length":"845"
                  },
                  "cssexchangeDistrictCode":"WN"
               },
               "technologyMessages":{
                  "technologyMessage":[
                     {
                        "code":"GEA",
                        "message":"Generic Ethernet Access"
                     }
                  ]
               },
               "productMessages":null,
               "estimatedSpeedInformation":{
                  "estimatedSpeedInformation":[
                     {
                        "estimatedSpeedInformationType":"Standard",
                        "estimatedAnnexADownstreamSpeed":"16097",
                        "estimatedAnnexAMaxRange":"19304",
                        "estimatedAnnexAMinRange":"9695",
                        "minThresholdAnnexA":"7431",
                        "estimatedAnnexMDownstreamSpeed":"14097",
                        "estimatedAnnexMUpstreamSpeed":"2000"
                     }
                  ]
               },
               "additionalInformation":null,
               "telephoneNumber":"01630656588",
               "accessLineID":"01630656588",
               "accessLineStatus":"BTLive",
               "postcode":"TF9 3DJ",
               "postcodeMatch":"YES",
               "currentCP":null,
               "currentReseller":null,
               "currentResellerAgentID":null,
               "numberRangeHolder":"BT",
               "careLevel":null,
               "serviceIncompatibility":null,
               "numberPortingInformation":"NotSet",
               "workingLineDetails":{
                  "partialDN":null,
                  "installationDN":null,
                  "productType":"PSTN Single Line",
                  "pendingCeaseOrderIndicator":"N",
                  "pendingCeaseCompletionDate":null,
                  "workingLineLocation":null,
                  "terminationType":null,
                  "installationType":null
               },
               "stoppedLineDetails":null,
               "dslspeedInformation":null
            }
         ]
      }
   }
}
```

## Virgin Media

http://localhost:8080/tt-linecharacteristics-service/linecharacteristics?telephoneNumber=01827708166&postCode=B772LY

```
{  
   "getLineCharacteristicsResult":{  
      "status":{  
         "hasErrors":true,
         "errors":{  
            "error":[  
               {  
                  "errorCode":"LCPTWS1004_AgentID_563",
                  "errorMessage":"Porting cannot be performed for Number Range Holder: Virgin Media",
                  "errorCategory":"REQUEST_ERROR",
                  "inputName":null,
                  "inputValue":null
               }
            ]
         }
      },
      "responseDetails":null
   }
}
```

## Cable and Wireless !!!

http://localhost:8080/tt-linecharacteristics-service/linecharacteristics?telephoneNumber=01211234567&postCode=B927HD

```
{  
   "getLineCharacteristicsResult":{  
      "status":{  
         "hasErrors":true,
         "errors":{  
            "error":[  
               {  
                  "errorCode":"LCPTWS1004_AgentID_37",
                  "errorMessage":"Porting cannot be performed for Number Range Holder: Cable & Wireless",
                  "errorCategory":"REQUEST_ERROR",
                  "inputName":null,
                  "inputValue":null
               }
            ]
         }
      },
      "responseDetails":null
   }
}
```

## Unknown Number

```
{  
   "getLineCharacteristicsResult":{  
      "status":{  
         "hasErrors":true,
         "errors":{  
            "error":[  
               {  
                  "errorCode":"LCPTWS1004_AgentID_0",
                  "errorMessage":"Porting cannot be performed for Number Range Holder: Unknown",
                  "errorCategory":"REQUEST_ERROR",
                  "inputName":null,
                  "inputValue":null
               }
            ]
         }
      },
      "responseDetails":null
   }
}
```

##  Error

```
{  
   "getLineCharacteristicsResult":{  
      "status":{  
         "hasErrors":true,
         "errors":{  
            "error":[  
               {  
                  "errorCode":"LCPTWS1010",
                  "errorMessage":null,
                  "errorCategory":"REQUEST_ERROR",
                  "inputName":null,
                  "inputValue":null
               }
            ]
         }
      },
      "responseDetails":null
   }
}
```
