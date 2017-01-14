package com.gigaspaces.kryo_example.service;

public interface ICartTrackerService {

  boolean touch(String cartId);

  void scan();

  void remove(String lineItemId);
}
