package com.cellnine.usfm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableStyle
@JsonSerialize(as = ImmutableVerse.class)
@JsonDeserialize(as = ImmutableVerse.class)
public abstract class Verse extends Element {
  public abstract int chapterNumber();
  public abstract int verseNumber();
  public abstract String text();
  @Value.Derived
  public String chapterId() {
    return String.format("%03d%03d", chapterNumber(), verseNumber());
  }
}
