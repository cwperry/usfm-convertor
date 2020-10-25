package com.cellnine.usfm;

import static org.assertj.core.api.Assertions.assertThat;

import com.cellnine.test.assertj.HtmlRepresentation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class HtmlConverterTest {

    private HtmlConverter converter;
    private String expectedHtml;

    @BeforeAll
    public static void init() {
        Assertions.useRepresentation(new HtmlRepresentation());
    }

    @BeforeEach
    public void beforeTests() throws IOException {
        List<String>  lines = Files.readAllLines(Path.of("src/test/resources/simple-book.usfm"), StandardCharsets.UTF_8);
        Book book = new UsfmToBookConverter().convert(lines);
        expectedHtml = Files.readString(Path.of("src/test/resources/simple-book.html"));
        converter = new HtmlConverter(book);
    }

    @Test
    public void render_book() {

        assertThat(converter.renderBook()).isEqualTo(expectedHtml);
    }

//    @Test
//    public void render_simple_paragraph() {
//        String input = "\\p This is a simple paragraph.";
//        String expected = "<p>This is a simple paragraph.</p>";
//
//        assertThat(converter.renderParagraph(input)).isEqualTo(expected);
//    }
//
//    @Test
//    public void render_simple_verse() {
//        String input = "\\v 18 And Naomi seeing that she was determined to go with her, ceased to speak to her " +
//            "any more.";
//        String expected = "<span class=\"verse\" rel=\"v008001018\" id=\"001018\" >18</span>\n" +
//            "    And Naomi seeing that she was determined to go with her, ceased to speak to her any more.";
//
//        assertThat(converter.renderVerse(input)).isEqualTo(expected);
//    }

}