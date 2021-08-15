package org.decoder.model;

import java.time.Instant;

public class Record {
  private final Instant creationDateTime;
  private final byte priority;
  private final double lat;
  private final double lon;
  private final int height;
  private final int azimuth;
  private final short numberOfSatellites;
  private final short speed;
  private final IOData ioData;

  public Record(
      Instant creationDateTime,
      byte priority,
      double lat,
      double lon,
      int height,
      int azimuth,
      short numberOfSatellites,
      short speed,
      IOData ioData
  ) {
    this.creationDateTime = creationDateTime;
    this.priority = priority;
    this.lat = lat;
    this.lon = lon;
    this.height = height;
    this.azimuth = azimuth;
    this.numberOfSatellites = numberOfSatellites;
    this.speed = speed;
    this.ioData = ioData;
  }

  public Instant getCreationDateTime() {
    return creationDateTime;
  }

  public byte getPriority() {
    return priority;
  }

  public double getLat() {
    return lat;
  }

  public double getLon() {
    return lon;
  }

  public int getHeight() {
    return height;
  }

  public int getAzimuth() {
    return azimuth;
  }

  public short getNumberOfSatellites() {
    return numberOfSatellites;
  }

  public short getSpeed() {
    return speed;
  }

  public IOData getIoData() {
    return ioData;
  }

  @Override
  public String toString() {
    return "Record{" +
           "creationDateTime=" + creationDateTime +
           ", priority=" + priority +
           ", lat=" + lat +
           ", lon=" + lon +
           ", height=" + height +
           ", azimuth=" + azimuth +
           ", numberOfSatellites=" + numberOfSatellites +
           ", speed=" + speed +
           ", ioData=" + ioData +
           '}';
  }
}
