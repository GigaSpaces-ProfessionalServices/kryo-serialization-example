package com.gigaspaces.kryo_example.common;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Externalizable;

public class Address implements Externalizable {

  public static final String STREET = "street";
  public static final String CITY = "city";
  public static final String COUNTRY = "country";

  private String street;

  private String city;

  private String country;

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
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
    output.writeString(street);
    output.writeString(city);
    output.writeString(country);
  }

  protected void read(final Kryo kryo, final Input input) {
    street = input.readString();
    city = input.readString();
    country = input.readString();
  }

  public Address(String street, String city, String country) {
    this.street = street;
    this.city = city;
    this.country = country;
  }

  public Address() {
    this("", "", "");
  }
}
