package com.gigaspaces.kryo_example.common;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class BillingAddress extends Address implements Externalizable {

  private String billedParty;

  public BillingAddress(String street, String city, String country, String billedParty) {
    super(street, city, country);
    this.billedParty = billedParty;
  }

  public BillingAddress() {
  }

  @Override
  public void writeExternal(final ObjectOutput stream) throws IOException {
    KryoSerializers.serialize(stream, this::write);
  }

  @Override
  public void readExternal(final ObjectInput stream) throws IOException, ClassNotFoundException {
    KryoSerializers.deserialize(stream, this::read);
  }

  protected void write(final Kryo kryo, final Output output) {
    super.write(kryo, output);
    output.writeString(billedParty);
  }

  protected void read(final Kryo kryo, final Input input) {
    super.read(kryo, input);
    billedParty = input.readString();
  }

}
