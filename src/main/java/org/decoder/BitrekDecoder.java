package org.decoder;

import java.nio.ByteBuffer;
import java.util.List;
import org.decoder.model.Record;
import org.decoder.parts.AVLPartDecoder;
import org.decoder.parts.PartDecoder;
import org.decoder.parts.PartDecoderContext;

/**
 * Bitrek decoder for packet inline with spec
 * https://gpsm.ua/downloads/Bitrek/ProtokolBitrek/BITREK_protocol_RU.pdf
 */
public class BitrekDecoder {

  private static final int CRC16_SIZE = 4;
  private final PartDecoder avlPartDecoder = new AVLPartDecoder();

  /**
   * Decodes data passed as input into set of records
   * @param input byte buffer with packed data
   * @return list of records
   * @throws IllegalArgumentException if input is invalid;
   * {@link IllegalStateException} if input is inconsistent;
   * {@link org.decoder.exceptions.UnsupportedEncodingFormatException} if CODEC_ID differs from 8.
   */
  //TODO add logging
  public List<Record> decode(ByteBuffer input){
    if (input == null || !input.hasRemaining()) {
      throw new IllegalArgumentException("BitrekDecoder input must be provided");
    }
    int preamble = input.getInt();
    if (preamble != 0) {
      throw new IllegalArgumentException("BitrekDecoder cannot detect preamble");
    }
    int datLen = input.getInt();
    if (input.remaining() - CRC16_SIZE != datLen) {
      throw new IllegalArgumentException("BitrekDecoder dat_len does not match AVL size");
    }
    PartDecoderContext context = new PartDecoderContext();
    avlPartDecoder.decodePart(input, context);
    // here should go checksum validation
    return context.records;
  }

}
