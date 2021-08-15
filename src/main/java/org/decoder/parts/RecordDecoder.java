package org.decoder.parts;

import java.nio.ByteBuffer;
import java.time.Instant;
import org.decoder.model.Record;
import org.decoder.parts.io.IODataPartDecoder;

public class RecordDecoder implements PartDecoder {

  private static final double COORDINATE_SCALE = Math.pow(10, -8);
  private final PartDecoder ioDataPartDecoder = new IODataPartDecoder();

  @Override
  public void decodePart(ByteBuffer input, PartDecoderContext context) {
    long creationDateMillis = input.getLong();
    var priority = input.get();

    var lat = input.getInt() * COORDINATE_SCALE;
    var lon = input.getInt() * COORDINATE_SCALE;

    var height = input.getShort();
    var azimuth = input.getShort();

    var numberOfSatellites = input.get();
    var speed = input.getShort();

    ioDataPartDecoder.decodePart(input, context);

    context.currentRecord = new Record(
        Instant.ofEpochMilli(creationDateMillis),
        priority,
        lat,
        lon,
        height,
        azimuth,
        numberOfSatellites,
        speed,
        context.ioData
    );
  }
}
