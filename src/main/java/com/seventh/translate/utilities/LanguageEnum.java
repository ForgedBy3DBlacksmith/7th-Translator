package com.seventh.translate.utilities;

public enum LanguageEnum {
    ENGLISH("EN"),
    JAPANESE("JA"),
    KOREAN("KO"),
    PORTUGUESE("PT");

    public final String langCode;

    LanguageEnum(String langCode) {
        this.langCode = langCode;
    }
}
