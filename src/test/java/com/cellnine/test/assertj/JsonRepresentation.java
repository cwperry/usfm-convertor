package com.cellnine.test.assertj;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.assertj.core.presentation.StandardRepresentation;

public class JsonRepresentation extends StandardRepresentation {

  private final ObjectMapper mapper;

  public JsonRepresentation() {
    mapper = new ObjectMapper();
    mapper.registerModule(new Jdk8Module());
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
