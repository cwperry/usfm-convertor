package com.cellnine.usfm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableStyle
@JsonSerialize(as = ImmutableTextElement.class)
@JsonDeserialize(as = ImmutableTextElement.class)
public abstract class TextElement extends Element {
  public abstract String text();
}
