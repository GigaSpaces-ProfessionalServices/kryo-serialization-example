package com.gigaspaces.kryo_example.common;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.annotation.pojo.SpaceStorageType;
import com.gigaspaces.metadata.StorageType;

import java.util.ArrayList;
import java.util.List;

@SpaceClass
public class Cart {

  private String user;
  private Long id;
  private List<LineItem> lineItems;
  private PaymentData paymentData;
  private ShippingAddress shippingAddress;
  private BillingAddress billingAddress;
  private Cost prize;

  public Cart() {
    lineItems = new ArrayList<>();
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  @SpaceId
  @SpaceRouting
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PaymentData getPaymentData() {
    return paymentData;
  }

  public void setPaymentData(PaymentData paymentData) {
    this.paymentData = paymentData;
  }

  @SpaceStorageType(storageType = StorageType.BINARY)
  public ShippingAddress getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(ShippingAddress shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  @SpaceStorageType(storageType = StorageType.BINARY)
  public BillingAddress getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(BillingAddress billingAddress) {
    this.billingAddress = billingAddress;
  }

  @SpaceStorageType(storageType = StorageType.BINARY)
  public Cost getPrize() {
    return prize;
  }

  public void setPrize(Cost prize) {
    this.prize = prize;
  }

  public List<LineItem> getLineItems() {
    return lineItems;
  }

  public void setLineItems(List<LineItem> lineItems) {
    this.lineItems = lineItems;
  }

  public boolean dropLineItem(String lineItemId) {
    boolean rv = false;
    LineItem lineItemToBeRemoved = null;
    for (LineItem lineItem : lineItems) {
      if (lineItemId.equals(lineItem.getId())) {
        lineItemToBeRemoved = lineItem;
        break;
      }
    }
    if (lineItemToBeRemoved != null) {
      lineItems.remove(lineItemToBeRemoved);
      rv = true;
    }
    return rv;
  }

  public void addLineItem(LineItem lineItem) {
    lineItems.add(lineItem);
  }

}
