package com.gigaspaces.kryo_example.common;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.gigaspaces.document.DocumentProperties;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LineItem implements Externalizable {

  public static final String NAME = "name";
  public static final String QUANTITY = "count";

  private String id;
  private List<Cost> discounts;
  private DocumentProperties data;

  public LineItem(long cartId, DocumentProperties data) {
    this.id = UUID.randomUUID().toString();
    this.data = data;
    discounts = new ArrayList<>();
  }

  public LineItem() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public DocumentProperties getData() {
    return data;
  }

  public void setData(DocumentProperties data) {
    this.data = data;
  }

  public List<Cost> getDiscounts() {
    return discounts;
  }

  public void setDiscounts(List<Cost> discounts) {
    this.discounts = discounts;
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
    output.writeString(id);
    kryo.writeObject(output, data);
    kryo.writeClassAndObject(output, discounts);
  }

  private void read(final Kryo kryo, final Input input) {
    id = input.readString();
    data = kryo.readObject(input, DocumentProperties.class);
    discounts = (ArrayList<Cost>) kryo.readClassAndObject(input);
  }
}
