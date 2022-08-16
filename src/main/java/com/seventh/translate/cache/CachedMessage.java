package com.seventh.translate.cache;

import java.time.Instant;
import java.util.Optional;

public class CachedMessage {
    String id;
    String content;
    Optional<Instant> edited_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Optional<Instant> getEdited_date() {
        return edited_date;
    }

    public void setEdited_date(Optional<Instant> edited_date) {
        this.edited_date = edited_date;
    }
}
