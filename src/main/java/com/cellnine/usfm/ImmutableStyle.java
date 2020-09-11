package com.cellnine.usfm;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(
    get = { "is*", "get*" },                      // Detect 'get' and 'is' prefixes in accessor methods
    init = "with*",                               // Builder initialization methods will have 'with' prefix
    typeImmutable = "Immutable*",                 // 'Immutable' prefix will be added to the generated class
    optionalAcceptNullable = true,                // For optional fields, withX(null) will be treated as empty
//        jdkOnly = true,                               // Generated code will use only JDK 7+ standard library classes
    visibility = Value.Style.ImplementationVisibility.PUBLIC) // Generated class will be always public
public @interface ImmutableStyle { }
