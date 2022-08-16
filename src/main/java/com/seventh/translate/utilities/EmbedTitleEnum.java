package com.seventh.translate.utilities;

public enum EmbedTitleEnum {
    EN("View Original Message"),
    JA("元のメッセージを表示"),
    KO("원본 메시지 보기"),
    PT("Ver mensagem original");

    public final String langTitle;

    EmbedTitleEnum(String langTitle) {
        this.langTitle = langTitle;
    }
}
