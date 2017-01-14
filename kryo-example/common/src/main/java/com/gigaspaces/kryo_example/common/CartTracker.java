package com.gigaspaces.kryo_example.common;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;
import com.gigaspaces.metadata.index.SpaceIndexType;

/**
 * Created by sudip on 11/22/2016.
 */
@SpaceClass
public class CartTracker {

  private String cartId;
  private long expirationTimestamp;

  @SpaceId
  public String getCartId() {
    return cartId;
  }

  public void setCartId(String cartId) {
    this.cartId = cartId;
  }

  @SpaceIndex(type = SpaceIndexType.EXTENDED)
  public long getExpirationTimestamp() {
    return expirationTimestamp;
  }

  public void setExpirationTimestamp(long timestamp) {
    expirationTimestamp = timestamp;
  }

  public CartTracker(String cartId, long expirationTimestamp) {
    this.cartId = cartId;
    this.expirationTimestamp = expirationTimestamp;
  }

  public CartTracker() {
  }
}
