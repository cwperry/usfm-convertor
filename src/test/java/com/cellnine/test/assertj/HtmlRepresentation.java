package com.cellnine.test.assertj;

import org.assertj.core.presentation.StandardRepresentation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlRepresentation extends StandardRepresentation {

  @Override
  protected String fallbackToStringOf(Object object) {
    System.out.println("object = " + object);
    if (object instanceof String && ((String) object).contains("<html")) {
      Document doc = Jsoup.parse((String) object);
      return doc.html();
    }
    return super.fallbackToStringOf(object);
  }

  @Override
  protected String toStringOf(String str) {
    if (str.contains("<html")) {
      Document doc = Jsoup.parse(str);
      return doc.html();
    }
    return str;
  }
}
