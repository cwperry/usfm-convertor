package com.cellnine.usfm;

import static j2html.TagCreator.*;

public class Main {

    public static void main(String[] args) {

        System.out.println(body(
                h1("Hello, World!")
        ).render());

    }
}
