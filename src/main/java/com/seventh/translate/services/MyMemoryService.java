package com.seventh.translate.services;

import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import com.seventh.translate.cache.CacheStore;
import com.seventh.translate.cache.CachedMessage;
import com.seventh.translate.utilities.LanguageErrorEnum;
import com.seventh.translate.mymemory.MyMemoryResponse;
import com.seventh.translate.mymemory.ResponseData;
import com.seventh.translate.utilities.TranslationErrorEnum;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;


@Component
public class MyMemoryService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${TRANSLATOR_TOKEN}")
    private String token;
    @Autowired
    CacheStore<CachedMessage> messageCache;

    public String findLanguage(String content) {
        final LanguageDetector detector = LanguageDetectorBuilder.fromLanguages(
                Language.PORTUGUESE,
                Language.ENGLISH,
                Language.KOREAN,
                Language.JAPANESE)
                .build();
        final Language detectedLanguage = detector.detectLanguageOf(content);
        return detectedLanguage.name();
    }

    private boolean isUriContent(String messageContent) {
        String [] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (urlValidator.isValid(messageContent)) {
            logger.info("message is a url");
            return true;
        } else {
            return false;
        }
    }

    private String cleanContentString(String content) {
        String firstEdit = content.replace("&lt;", "<");
        String finalEdit = firstEdit.replace("&gt;", ">");
        return finalEdit;
    }

    private String getTranslationFromApi(String messageContent, String lang, String detectedLanguage, String messageId, Optional<Instant> edited_date) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MyMemoryResponse> response = restTemplate.getForEntity("https://api.mymemory.translated.net/get?q=" + messageContent + "&langpair=" + lang + "&key=" + token, MyMemoryResponse.class);

        MyMemoryResponse body = response.getBody();
        ResponseData responseBody = body.getResponseData();
        if (!responseBody.getTranslatedText().isBlank()) {
            String rawRes = responseBody.getTranslatedText();

            String bodyRes = cleanContentString(rawRes);

            CachedMessage cachedMessage = new CachedMessage();
            cachedMessage.setContent(bodyRes);
            cachedMessage.setId(messageId);
            cachedMessage.setEdited_date(edited_date);

            logger.info("caching translated message ....");
            messageCache.add(messageId, cachedMessage);
            logger.info("translation complete");

            return bodyRes;
        } else {
            logger.error("Unable to get translation: " + body);
            return TranslationErrorEnum.valueOf(detectedLanguage).translationErr;
        }
    }

    public String translateText(String messageContent, String lang, String detectedLanguage, String messageId, Optional<Instant> edited_date) {
        logger.info("Getting translation ...");
        CachedMessage retrievedCache = messageCache.get(messageId);

        if (retrievedCache != null) {
            if (!retrievedCache.getEdited_date().equals(edited_date)) {
                messageCache.invalidate(messageId);
                logger.info("updating cache for updated message...");
                return getTranslationFromApi(messageContent, lang, detectedLanguage, messageId, edited_date);
            } else {
                logger.info("retrieved translated message from cache!");
                return retrievedCache.getContent();
            }
        } else {
            return getTranslationFromApi(messageContent, lang, detectedLanguage, messageId, edited_date);
        }
    }


    public String getTranslation(String messageContent, String lang, boolean isIdenticalLang, String detectedLanguage, String message_id, Optional<Instant> edited_date) {
        if (isUriContent(messageContent)) {
            return "\uD83D\uDEAB\uD83D\uDEAB\uD83D\uDEAB Unable to translate urls! \uD83D\uDEAB\uD83D\uDEAB\uD83D\uDEAB";
        }
        if (isIdenticalLang) {
            return LanguageErrorEnum.valueOf(detectedLanguage.toUpperCase()).errorMessage;
        } else {
            return translateText(messageContent, lang, detectedLanguage, message_id, edited_date);
        }
    }
}
