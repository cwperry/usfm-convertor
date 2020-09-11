package com.cellnine.usfm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsfmToBookConverter {
  private static final String NOT_FOUND = "Not Found";

  public Book convert(List<String> lines) {
    String id = lines.stream()
        .filter(l -> l.contains("\\id"))
        .map(n -> n.split(" ")[1])
        .findFirst()
        .orElse(NOT_FOUND);
    return ImmutableBook.builder()
        .withId(id)
        .withName(lines.stream()
                      .filter((l -> l.contains("\\toc1")))
                      .map(this::stripUsfmTag)
                      .findFirst()
                      .orElse(NOT_FOUND))
        .withTableOfContents2(lines.stream()
                                  .filter((l -> l.contains("\\toc2")))
                                  .map(this::stripUsfmTag)
                                  .findFirst())
        .withTableOfContents3(lines.stream()
                                  .filter((l -> l.contains("\\toc3")))
                                  .map(this::stripUsfmTag)
                                  .findFirst())
        .withMajorTitle(lines.stream()
                            .filter((l -> l.contains("\\mt1")))
                            .map(this::stripUsfmTag)
                            .findFirst()
                            .orElse(NOT_FOUND))
        .withMajorTitle2(lines.stream()
                             .filter((l -> l.contains("\\mt2")))
                             .map(this::stripUsfmTag)
                             .findFirst())
        .withMajorTitle3(lines.stream()
                             .filter((l -> l.contains("\\mt3")))
                             .map(this::stripUsfmTag)
                             .findFirst())
        .withChapters(buildChapters(id, lines))
        .build();
  }

  private List<Chapter> buildChapters(String bookName, List<String> lines) {
    List<Chapter> chapters = new ArrayList<>();
    List<Integer> chapterIndexes = indexesForTag(lines, "\\c");
    List<String> chapterLines;
    for (int i = 0; i < chapterIndexes.size(); i++) {
      Integer chapterIndex = chapterIndexes.get(i);
      if (!chapterIndex.equals(chapterIndexes.get(chapterIndexes.size() - 1))) {
        chapterLines = lines.subList(chapterIndex, chapterIndexes.get(i + 1));
      } else {
        chapterLines = lines.subList(chapterIndex, lines.size());
      }
      chapters.add(buildChapter(bookName, chapterLines));

    }
    return chapters;
  }

  private List<Integer> indexesForTag(List<String> lines, String tag) {
    List<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      if (lines.get(i).startsWith(tag)) {
        indexes.add(i);
      }
    }
    return indexes;
  }

  private Chapter buildChapter(String bookName, List<String> chapterLines) {
    int chapterNumber = Integer.parseInt(stripUsfmTag(chapterLines.get(0)));
    return ImmutableChapter.builder()
        .withBook(bookName)
        .withChapterNumber(chapterNumber)
        .withParagraphs(buildParagraphs(chapterNumber, chapterLines))
        .build();
  }

  private List<Paragraph> buildParagraphs(int chapterNumber, List<String> chapterLines) {
    List<Paragraph> paragraphs = new ArrayList<>();
    List<Integer> paragraphIndexes = indexesForTag(chapterLines, "\\p");
    List<String> paragraphLines;
    for (int i = 0; i < paragraphIndexes.size(); i++) {
      Integer paragraphIndex = paragraphIndexes.get(i);
      if (!paragraphIndex.equals(paragraphIndexes.get(paragraphIndexes.size() - 1))) {
        paragraphLines = chapterLines.subList(paragraphIndex, paragraphIndexes.get(i + 1));
      } else {
        paragraphLines = chapterLines.subList(paragraphIndex, chapterLines.size());
      }
      paragraphs.add(buildParagraph(chapterNumber, paragraphLines));

    }
    return paragraphs;
  }

  private Paragraph buildParagraph(int chapterNumber, List<String> paragraphLines) {
    List<Element> elements = new ArrayList<>();
    for (String line : paragraphLines) {
      if (line.contains("\\p") && line.trim().length() < 3) {
        continue;
      }
      if (line.startsWith("\\v")) {
        elements.add(buildVerse(chapterNumber, line));
      }
      if (!line.startsWith("\\")) {
        elements.add(ImmutableTextElement.builder().withText(line).build());
      }
    }
    return ImmutableParagraph.builder()
        .withElements(elements)
        .build();
  }

  private Verse buildVerse(int chapter, String input) {
    String lineWithoutTag = stripUsfmTag(input);
    int verseNumber = Integer.parseInt(lineWithoutTag.substring(0, lineWithoutTag.indexOf(" ")));
    String text = lineWithoutTag.substring(lineWithoutTag.indexOf(" ") + 1);
    return ImmutableVerse.builder()
        .withChapterNumber(chapter)
        .withVerseNumber(verseNumber)
        .withText(text)
        .build();
  }

  private String stripUsfmTag(String input) {
    return input.substring(input.indexOf(" ") + 1);
  }
}
