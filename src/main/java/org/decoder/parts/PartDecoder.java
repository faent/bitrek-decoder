package org.decoder.parts;

import java.nio.ByteBuffer;

public interface PartDecoder {

  void decodePart(ByteBuffer input, PartDecoderContext context);
}
