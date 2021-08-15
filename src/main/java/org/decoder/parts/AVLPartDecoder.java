package org.decoder.parts;

import java.nio.ByteBuffer;
import org.decoder.exceptions.UnsupportedEncodingFormatException;

public class AVLPartDecoder implements PartDecoder {

  private static final int SUPPORTED_ENCODING = 8;

  private final PartDecoder recordDecoder = new RecordDecoder();

  @Override
  public void decodePart(ByteBuffer input, PartDecoderContext context) {
    byte codecId = input.get();
    if (codecId != SUPPORTED_ENCODING) {
      throw new UnsupportedEncodingFormatException("BitrekDecoder received unsupported CODEC_ID");
    }
    context.codecId = codecId;
    context.totalRecords = input.get();
    byte currentRecordNumber = 0;
    while (currentRecordNumber < context.totalRecords) {
      currentRecordNumber++;
      recordDecoder.decodePart(input, context);
      context.flushCurrentRecordToList();
    }
    byte confirmTotalRecords = input.get();
    if (context.totalRecords != confirmTotalRecords) {
      throw new IllegalStateException("BitrekDecoder AVL total records numbers do not match");
    }
  }
}
