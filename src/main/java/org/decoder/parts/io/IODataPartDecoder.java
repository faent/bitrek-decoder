package org.decoder.parts.io;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.decoder.model.EightByteIOElement;
import org.decoder.model.FourByteIOElement;
import org.decoder.model.IOData;
import org.decoder.model.IOElement;
import org.decoder.model.OneByteIOElement;
import org.decoder.model.TwoByteIOElement;
import org.decoder.parts.PartDecoder;
import org.decoder.parts.PartDecoderContext;

public class IODataPartDecoder implements PartDecoder {

  private static final byte ONE_BYTE_CAPACITY = 1;
  private static final byte TWO_BYTE_CAPACITY = 2;
  private static final byte FOUR_BYTE_CAPACITY = 4;
  private static final byte EIGHT_BYTE_CAPACITY = 8;
  private final Map<Byte, PartDecoder> ioPartDecodersByCapacity =
      Map.of(
          ONE_BYTE_CAPACITY, new OneByteIOPartDecoder(),
          TWO_BYTE_CAPACITY, new TwoByteIOPartDecoder(),
          FOUR_BYTE_CAPACITY, new FourByteIOPartDecoder(),
          EIGHT_BYTE_CAPACITY, new EightByteIOPartDecoder()
      );

  @Override
  public void decodePart(ByteBuffer input, PartDecoderContext context) {
    byte eventIOId = input.get();
    byte numberOfTotalIO = input.get();

    var oneByteIOElements = (List<OneByteIOElement>) decodeIO(input, context, ONE_BYTE_CAPACITY);
    var twoByteIOElements = (List<TwoByteIOElement>) decodeIO(input, context, TWO_BYTE_CAPACITY);
    var fourByteIOElements = (List<FourByteIOElement>) decodeIO(input, context, FOUR_BYTE_CAPACITY);
    var eightByteIOElements = (List<EightByteIOElement>) decodeIO(input, context, EIGHT_BYTE_CAPACITY);

    context.ioData = new IOData(eventIOId, numberOfTotalIO, oneByteIOElements, twoByteIOElements,
                                fourByteIOElements, eightByteIOElements);
  }

  private List<? extends IOElement> decodeIO(ByteBuffer input, PartDecoderContext context,
                                       byte capacity) {
    PartDecoder ioPartDecoder = ioPartDecodersByCapacity.get(capacity);
    if (ioPartDecoder == null) {
      throw new IllegalStateException("BitrekDecoder could not find IO decoder by capacity");
    }
    byte numberOfTotalIO = input.get();
    context.ioElements = new ArrayList<>();
    byte currentIO = 0;
    while (currentIO < numberOfTotalIO) {
      currentIO++;
      ioPartDecoder.decodePart(input, context);
    }
    return context.ioElements;
  }
}
