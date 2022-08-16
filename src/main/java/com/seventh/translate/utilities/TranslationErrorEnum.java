package com.seventh.translate.utilities;

public enum TranslationErrorEnum {
    EN("⚠️ Unable to get translation! ⚠️"),
    JA("⚠️ 翻訳を取得できません! ⚠️"),
    KO("⚠️ 번역을 받을 수 없습니다! ⚠️"),
    PT("⚠️ Não foi possível obter a tradução! ⚠️");

    public final String translationErr;

    TranslationErrorEnum(String translationErr) {
        this.translationErr = translationErr;
    }
}
