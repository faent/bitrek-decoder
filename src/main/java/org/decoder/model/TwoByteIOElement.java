package org.decoder.model;

public class TwoByteIOElement implements IOElement {
  private final byte id;
  private final short value;

  public TwoByteIOElement(byte id, short value) {
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
    return "TwoByteIOElement{" +
           "id=" + id +
           ", value=" + value +
           '}';
  }
}
