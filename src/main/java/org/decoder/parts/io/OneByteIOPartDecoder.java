package org.decoder.parts.io;

import java.nio.ByteBuffer;
import org.decoder.model.OneByteIOElement;
import org.decoder.parts.PartDecoder;
import org.decoder.parts.PartDecoderContext;

public class OneByteIOPartDecoder implements PartDecoder {

  @Override
  public void decodePart(ByteBuffer input, PartDecoderContext context) {
    byte id = input.get();
    byte value = input.get();
    context.ioElements.add(new OneByteIOElement(id, value));
  }
}
