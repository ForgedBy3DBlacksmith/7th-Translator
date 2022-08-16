package com.seventh.translate.mymemory;

public class ResponseData {
    private String translatedText;
    private float match;


    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public float getMatch() {
        return match;
    }

    public void setMatch(float match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "translatedText='" + translatedText + '\'' +
                ", match=" + match +
                '}';
    }
}
