package org.decoder.parts.io;

import java.nio.ByteBuffer;
import org.decoder.model.TwoByteIOElement;
import org.decoder.parts.PartDecoder;
import org.decoder.parts.PartDecoderContext;

public class TwoByteIOPartDecoder implements PartDecoder {

  @Override
  public void decodePart(ByteBuffer input, PartDecoderContext context) {
    byte id = input.get();
    short value = input.getShort();
    context.ioElements.add(new TwoByteIOElement(id, value));
  }
}
