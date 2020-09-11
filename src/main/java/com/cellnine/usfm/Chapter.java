package com.cellnine.usfm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import java.util.List;

@Value.Immutable
@ImmutableStyle
@JsonSerialize(as = ImmutableChapter.class)
@JsonDeserialize(as = ImmutableChapter.class)
public abstract class Chapter extends Element {
  public abstract String book();

  public abstract int chapterNumber();

  public abstract List<Paragraph> paragraphs();

  @Value.Derived
  public String chapterId() {
    return String.format("%s%03d", book(), chapterNumber());
  }
}
