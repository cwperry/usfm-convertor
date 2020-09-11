package com.cellnine.usfm;

import static org.assertj.core.api.Assertions.assertThat;

import com.cellnine.test.assertj.JsonRepresentation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class UsfmToBookConverterTest {

  private UsfmToBookConverter converter;

  @BeforeAll
  public static void init() {
    Assertions.useRepresentation(new JsonRepresentation());
  }

  @BeforeEach
  public void beforeTests() {
    converter = new UsfmToBookConverter();
  }

  @Test
  public void converts_usfm_to_book() throws IOException {
    List<String> lines = Files.readAllLines(Path.of("src/test/resources/simple-book.usfm"), StandardCharsets.UTF_8);
    Book expected = createBook();

    assertThat(converter.convert(lines)).isEqualTo(expected);

  }

  private Book createBook() {
    List<Chapter> chapters = createChapters();
    return ImmutableBook.builder()
        .withId("SIM")
        .withName("The Simple Book")
        .withTableOfContents2("Simple")
        .withTableOfContents3("Sim")
        .withMajorTitle("The Simple Book")
        .withMajorTitle2("Smaller Title")
        .withMajorTitle3("Smallest Title")
        .withChapters(chapters)
        .build();
  }

  private List<Chapter> createChapters() {
    List<Chapter> chapters = new ArrayList<>();
    Chapter one = ImmutableChapter.builder()
        .withBook("SIM")
        .withChapterNumber(1)
        .withParagraphs(createParagraphs(1, "one"))
        .build();
    chapters.add(one);
    Chapter two = ImmutableChapter.builder()
        .withBook("SIM")
        .withChapterNumber(2)
        .withParagraphs(createParagraphs(2, "two"))
        .build();
    chapters.add(two);
    return chapters;
  }

  private List<Paragraph> createParagraphs(int chapterNumber, String chapterString) {
    List<Paragraph> paragraphs = new ArrayList<>();

    Verse vOne = ImmutableVerse.builder()
        .withChapterNumber(chapterNumber)
        .withVerseNumber(1)
        .withText("Chapter " + chapterString + ", verse one.")
        .build();
    Verse vTwo = ImmutableVerse.builder()
        .withChapterNumber(chapterNumber)
        .withVerseNumber(2)
        .withText("Chapter " + chapterString + ", verse two.")
        .build();
    List<Element> pOneVerses = new ArrayList<>();
    pOneVerses.add(vOne);
    pOneVerses.add(vTwo);
    Paragraph one = ImmutableParagraph.builder()
        .withElements(pOneVerses)
        .build();
    paragraphs.add(one);

    Verse vThree = ImmutableVerse.builder()
        .withChapterNumber(chapterNumber)
        .withVerseNumber(3)
        .withText("Chapter " + chapterString + ", verse three.")
        .build();
    Verse vFour = ImmutableVerse.builder()
        .withChapterNumber(chapterNumber)
        .withVerseNumber(4)
        .withText("Chapter " + chapterString + ", verse four.")
        .build();
    List<Element> pTwoVerses = new ArrayList<>();
    pTwoVerses.add(vThree);
    pTwoVerses.add(vFour);
    Paragraph two = ImmutableParagraph.builder()
        .withElements(pTwoVerses)
        .build();
    paragraphs.add(two);

    return paragraphs;
  }

}