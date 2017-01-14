package com.gigaspaces.kryo_example.common;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ShippingAddress extends Address implements Externalizable {

  private String type; // "residence" / "office"

  public ShippingAddress(String street, String city, String country, String type) {
    super(street, city, country);
    this.type = type;
  }

  public ShippingAddress() {
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public void writeExternal(final ObjectOutput stream) throws IOException {
    KryoSerializers.serialize(stream, this::write);
  }

  @Override
  public void readExternal(final ObjectInput stream) throws IOException, ClassNotFoundException {
    super.readExternal(stream);
  }

  protected void write(final Kryo kryo, final Output output) {
    super.write(kryo, output);
    output.writeString(type);
  }

  protected void read(final Kryo kryo, final Input input) {
    super.read(kryo, input);
    type = input.readString();
  }

}
