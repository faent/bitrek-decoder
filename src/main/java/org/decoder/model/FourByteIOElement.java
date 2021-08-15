package org.decoder.model;

public class FourByteIOElement implements IOElement {
  private final byte id;
  private final int value;

  public FourByteIOElement(byte id, int value) {
    this.id = id;
    this.value = value;
  }

  @Override
  public byte getId() {
    return id;
  }

  @Override
  public long getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "FourByteIOElement{" +
           "id=" + id +
           ", value=" + value +
           '}';
  }
}
