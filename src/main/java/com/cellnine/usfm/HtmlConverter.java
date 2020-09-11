package com.cellnine.usfm;

import static j2html.TagCreator.*;
import static j2html.attributes.Attr.CHARSET;

public class HtmlConverter {

  private final Book book;

  public HtmlConverter(Book book) {
    this.book = book;
  }

  public String renderBook() {
    return html(
        head(
            title(String.format("New Era Bible | %s", book.name())),
            meta().attr(CHARSET, "utf-8"),
            link().withHref("normalize.css")
                .withRel("stylesheet"),
            link().withHref("normalize.css")
                .withRel("stylesheet")),
        body(
            h1(book.name()),
            iff(book.tableOfContents2()
                    .isPresent(),
                h2(book.tableOfContents2()
                       .get())),
            iff(book.tableOfContents3()
                    .isPresent(),
                h3(book.tableOfContents3()
                       .get())))
//                each(book.chapters(), chapter -> p(span().withClass("chapter").withId("001001"),
//                     each(chapter.paragraphs())))
//                )

               ).render();
  }


}
