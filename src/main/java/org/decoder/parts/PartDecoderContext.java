package org.decoder.parts;

import java.util.ArrayList;
import java.util.List;
import org.decoder.model.IOData;
import org.decoder.model.IOElement;
import org.decoder.model.Record;

public class PartDecoderContext {
  public byte codecId;
  public byte totalRecords;
  public List<Record> records;
  public Record currentRecord;
  public List<IOElement> ioElements;
  public IOData ioData;

  public PartDecoderContext() {
    this.records = new ArrayList<>();
  }

  public void flushCurrentRecordToList() {
    if (currentRecord != null) {
      records.add(currentRecord);
      this.currentRecord = null;
    }
  }

  @Override
  public String toString() {
    return "PartDecoderContext{" +
           "codecId=" + codecId +
           ", totalRecords=" + totalRecords +
           ", records=" + records +
           ", currentRecord=" + currentRecord +
           ", ioElements=" + ioElements +
           ", ioData=" + ioData +
           '}';
  }
}
