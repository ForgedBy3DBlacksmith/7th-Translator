package com.seventh.translate.utilities;

public enum LanguageErrorEnum {
    EN("This text is already written English!"),
    JA("このテキストはすでに日本語で書かれています。"),
    KO("이 텍스트는 이미 한국어로 작성되었습니다!"),
    PT("Este texto já está escrito em português!");

    public final String errorMessage;

    LanguageErrorEnum(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
