package com.gigaspaces.kryo_example.common;

import com.gigaspaces.document.DocumentProperties;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Serializable;

public class PaymentData implements Serializable {

  public static final String CARD_NUMBER = "cardNumber";
  public static final String HOLDER_NAME = "holderName";
  public static final String VALID_UPTO = "validUpto";
  public static final String CVV_CODE = "cvvCode";

  private DocumentProperties info;

  public PaymentData(String paymentJson) {
    JsonParser parser = new JsonParser();
    JsonObject jsonObject = (JsonObject) parser.parse(paymentJson);
    String cardNumber = jsonObject.get(CARD_NUMBER).getAsString();
    String holderName = jsonObject.get(HOLDER_NAME).getAsString();
    String validUpto = jsonObject.get(VALID_UPTO).getAsString();
    String cvvCode = jsonObject.get(CVV_CODE).getAsString();
    info = new DocumentProperties()
            .setProperty(CARD_NUMBER, cardNumber)
            .setProperty(HOLDER_NAME, holderName)
            .setProperty(VALID_UPTO, validUpto)
            .setProperty(CVV_CODE, cvvCode)
    ;
  }

  public PaymentData() {
  }

  public DocumentProperties getInfo() {
    return info;
  }

}
