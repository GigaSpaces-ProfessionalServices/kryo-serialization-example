package com.gigaspaces.kryo_example.service;

import org.openspaces.remoting.Routing;

public interface ICartService {

  String getCart(@Routing long cartId);

  boolean isCartExist(long cartId);

  boolean createCart(@Routing long cartId, String cartJson);

  boolean updateCart(@Routing long cartId, String cartJson);

  void updatePaymentData(@Routing long cartId, String paymentJson);

  void scan();

  void touch(String lineItemId);

}
