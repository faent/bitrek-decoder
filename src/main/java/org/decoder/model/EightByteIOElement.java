package org.decoder.model;

public class EightByteIOElement implements IOElement {
  private final byte id;
  private final long value;

  public EightByteIOElement(byte id, long value) {
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
    return "EightByteIOElement{" +
           "id=" + id +
           ", value=" + value +
           '}';
  }
}
