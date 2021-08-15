package org.decoder;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.decoder.model.FourByteIOElement;
import org.decoder.model.IOData;
import org.decoder.model.OneByteIOElement;
import org.decoder.model.Record;
import org.junit.jupiter.api.Test;

import static com.github.reflectionassert.ReflectionAssertions.assertLenientThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BitrekDecoderTest {

  BitrekDecoder bitrekDecoder = new BitrekDecoder();

  @Test
  void shouldThrowIllegalArgumentWhenInputIsNull() {
    assertThrows(IllegalArgumentException.class, () -> bitrekDecoder.decode(null));
  }

  @Test
  void shouldThrowIllegalArgumentWhenInputIsEmpty() {
    assertThrows(
        IllegalArgumentException.class,
        () -> bitrekDecoder.decode(ByteBuffer.allocate(0))
    );
  }

  @Test
  void shouldThrowIllegalArgumentWhenPreambleIsInvalid() throws DecoderException {
    //arrange
    String testData =
        "000000" //preamble
        + "0000002c" //dat_len
        + "080100000113fc208dff00209cca800f14f650006f00d604000500040301011503160"
        + "30001460000015d0001"
        + "00000001"; //possible checksum
    byte[] testBytes = Hex.decodeHex(testData);
    ByteBuffer input = ByteBuffer.wrap(testBytes);

    //assert
    assertThrows(IllegalArgumentException.class, () -> bitrekDecoder.decode(input));
  }

  @Test
  void shouldThrowIllegalArgumentWhenDatLenDoesntMatchRealSize() throws DecoderException {
    //arrange
    String testData =
        "00000000" //preamble
        + "0000001c" //dat_len
        + "080100000113fc208dff00209cca800f14f650006f00d604000500040301011503160"
        + "30001460000015d0001"
        + "00000001"; //possible checksum
    byte[] testBytes = Hex.decodeHex(testData);
    ByteBuffer input = ByteBuffer.wrap(testBytes);

    //assert
    assertThrows(IllegalArgumentException.class, () -> bitrekDecoder.decode(input));
  }

  @Test
  void shouldCorrectlyDecodeDataFromRecord() throws DecoderException {
    //arrange
    String testData =
        "00000000" //preamble
        + "0000002c" //dat_len
        + "080100000113fc208dff00209cca800f14f650006f00d604000500040301011503160"
                      + "30001460000015d0001"
        + "00000001"; //possible checksum
    byte[] testBytes = Hex.decodeHex(testData);
    ByteBuffer input = ByteBuffer.wrap(testBytes);

    //act
    List<Record> decodedRecords = bitrekDecoder.decode(input);

    //assert
    byte zero = 0;
    byte one = 1;
    byte three = 3;
    byte twentyOne = 21;
    byte twentyTwo = 22;
    Record expected = new Record(
        LocalDateTime.of(2007, 7, 25, 6, 46, 38, 335000000).toInstant(ZoneOffset.UTC),
        zero,
        5.47146368d,
        2.53032016d,
        111,
        214,
        (short) 4,
        (short) 5,
        new IOData(
            zero,
            (byte) 4,
            List.of(
                new OneByteIOElement(one, one),
                new OneByteIOElement(twentyOne, three),
                new OneByteIOElement(twentyTwo, three)
            ),
            List.of(),
            List.of(new FourByteIOElement((byte) 70, 349)),
            List.of()
        )
    );
    assertThat(decodedRecords).hasOnlyOneElementSatisfying(
        actual -> assertLenientThat(actual).isEqualTo(expected)
    );
  }
}
