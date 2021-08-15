package org.decoder.model;

public class OneByteIOElement implements IOElement {
  private final byte id;
  private final byte value;

  public OneByteIOElement(byte id, byte value) {
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
    return "OneByteIOElement{" +
           "id=" + id +
           ", value=" + value +
           '}';
  }
}
