package com.cellnine.usfm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import java.util.List;

@Value.Immutable
@ImmutableStyle
@JsonSerialize(as = ImmutableParagraph.class)
@JsonDeserialize(as = ImmutableParagraph.class)
public interface Paragraph {
  List<Element> elements();
}
