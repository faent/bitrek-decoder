package org.decoder.parts.io;

import java.nio.ByteBuffer;
import org.decoder.model.EightByteIOElement;
import org.decoder.parts.PartDecoder;
import org.decoder.parts.PartDecoderContext;

public class EightByteIOPartDecoder implements PartDecoder {

  @Override
  public void decodePart(ByteBuffer input, PartDecoderContext context) {
    byte id = input.get();
    long value = input.getLong();
    context.ioElements.add(new EightByteIOElement(id, value));
  }
}
