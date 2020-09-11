package com.cellnine.usfm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import java.util.List;
import java.util.Optional;

@Value.Immutable
@ImmutableStyle
@JsonSerialize(as = ImmutableBook.class)
@JsonDeserialize(as = ImmutableBook.class)
public interface Book {
  String name();
  String id();
  Optional<String> tableOfContents2();
  Optional<String> tableOfContents3();
  String majorTitle();
  Optional<String> majorTitle2();
  Optional<String> majorTitle3();
  List<Chapter> chapters();
}
