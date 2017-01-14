package com.gigaspaces.kryo_example.common;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.gigaspaces.document.DocumentProperties;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;

public class Cost implements Externalizable {

  private String name;
  private BigDecimal amount;
  private DocumentProperties properties;

  public Cost(String name, BigDecimal amount) {
    this.name = name;
    this.amount = amount;
    properties = new DocumentProperties();
  }

  public Cost() {
    this("", new BigDecimal(0));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public DocumentProperties getProperties() {
    return properties;
  }

  public void setProperties(DocumentProperties properties) {
    this.properties = properties;
  }

  @Override
  public void writeExternal(final ObjectOutput stream) throws IOException {
    KryoSerializers.serialize(stream, this::write);
  }

  @Override
  public void readExternal(final ObjectInput stream) throws IOException, ClassNotFoundException {
    KryoSerializers.deserialize(stream, this::read);
  }

  private void write(final Kryo kryo, final Output output) {
    output.writeString(name);
    kryo.writeObject(output, amount);
    kryo.writeObject(output, properties);
  }

  private void read(final Kryo kryo, final Input input) {
    name = input.readString();
    amount = kryo.readObject(input, BigDecimal.class);
    properties = kryo.readObject(input, DocumentProperties.class);
  }


}
