package org.decoder.parts.io;

import java.nio.ByteBuffer;
import org.decoder.model.FourByteIOElement;
import org.decoder.parts.PartDecoder;
import org.decoder.parts.PartDecoderContext;

public class FourByteIOPartDecoder implements PartDecoder {

  @Override
  public void decodePart(ByteBuffer input, PartDecoderContext context) {
    byte id = input.get();
    int value = input.getInt();
    context.ioElements.add(new FourByteIOElement(id, value));
  }
}
