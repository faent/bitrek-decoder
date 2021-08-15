package org.decoder.model;

import java.util.List;

public class IOData {
  private final byte eventIOId;
  private final byte numberOfTotalIO;
  private final List<OneByteIOElement> oneByteIO;
  private final List<TwoByteIOElement> twoByteIO;
  private final List<FourByteIOElement> fourByteIO;
  private final List<EightByteIOElement> eightByteIO;

  public IOData(
      byte eventIOId,
      byte numberOfTotalIO,
      List<OneByteIOElement> oneByteIO,
      List<TwoByteIOElement> twoByteIO,
      List<FourByteIOElement> fourByteIO,
      List<EightByteIOElement> eightByteIO
  ) {
    this.eventIOId = eventIOId;
    this.numberOfTotalIO = numberOfTotalIO;
    this.oneByteIO = List.copyOf(oneByteIO);
    this.twoByteIO = List.copyOf(twoByteIO);
    this.fourByteIO = List.copyOf(fourByteIO);
    this.eightByteIO = List.copyOf(eightByteIO);
  }

  public byte getEventIOId() {
    return eventIOId;
  }

  public byte getNumberOfTotalIO() {
    return numberOfTotalIO;
  }

  public List<OneByteIOElement> getOneByteIO() {
    return oneByteIO;
  }

  public List<TwoByteIOElement> getTwoByteIO() {
    return twoByteIO;
  }

  public List<FourByteIOElement> getFourByteIO() {
    return fourByteIO;
  }

  public List<EightByteIOElement> getEightByteIO() {
    return eightByteIO;
  }

  @Override
  public String toString() {
    return "IOData{" +
           "eventIOId=" + eventIOId +
           ", numberOfTotalIO=" + numberOfTotalIO +
           ", oneByteIO=" + oneByteIO +
           ", twoByteIO=" + twoByteIO +
           ", fourByteIO=" + fourByteIO +
           ", eightByteIO=" + eightByteIO +
           '}';
  }
}
