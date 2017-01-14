package com.gigaspaces.kryo_example.common;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.gigaspaces.document.DocumentProperties;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.util.function.BiConsumer;

public class KryoSerializers {

  private static final ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
    protected Kryo initialValue() {
      Kryo kryo = new Kryo();
      kryo.register(Address.class);
      kryo.register(ShippingAddress.class);
      kryo.register(BillingAddress.class);
      kryo.register(Cost.class);
      kryo.register(BigDecimal.class);
      kryo.register(DocumentProperties.class);
      return kryo;
    };
  };

  public static void serialize(final ObjectOutput out, final BiConsumer<Kryo, Output> serialize) throws IOException {
    Kryo kryo = kryos.get();
    ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
    Output output = new Output(byteOutStream);
    serialize.accept(kryo, output);
    output.close();
    byte[] bytesToWrite = byteOutStream.toByteArray();
    out.writeInt(bytesToWrite.length); // write number of bytes that got written by kryo
    out.write(bytesToWrite);
  }

  public static void deserialize(final ObjectInput in, final BiConsumer<Kryo, Input> deserialize) throws IOException {
    Kryo kryo = kryos.get();
    int bytesToRead = in.readInt(); // see how many bytes were written and allocate according buffer
    byte[] buffer = new byte[bytesToRead];
    in.read(buffer);
    Input input = new Input(buffer);
    deserialize.accept(kryo, input);
    input.close();
  }

}
