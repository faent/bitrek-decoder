package org.decoder.parts;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.decoder.model.Record;
import org.junit.jupiter.api.Test;

import static com.github.reflectionassert.ReflectionAssertions.assertLenientThat;

class AVLPartDecoderTest {

  PartDecoder avlDecoder = new AVLPartDecoder();

}
