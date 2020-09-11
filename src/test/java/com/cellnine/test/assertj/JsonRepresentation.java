package com.cellnine.test.assertj;

import com.cellnine.usfm.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.presentation.StandardRepresentation;

public class JsonRepresentation extends StandardRepresentation {

  private final ObjectMapper mapper;

  public JsonRepresentation() {
    mapper = new ObjectMapper();
  }

  @Override
  protected String fallbackToStringOf(Object object) {
    try {
      return mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(object);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return super.fallbackToStringOf(object);
  }
}
