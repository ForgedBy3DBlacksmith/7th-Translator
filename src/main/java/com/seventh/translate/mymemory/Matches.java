package com.seventh.translate.mymemory;

public class Matches {
    private String id;
    private String segment;
    private String translation;
    private String source;
    private String target;
    private String quality;
    private String reference;
    private int usage_count;
    private String subject;
    private String created_by;
    private String last_updated_by;
    private String created;
    private String last_update_date;
    private float match;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getUsage_count() {
        return usage_count;
    }

    public void setUsage_count(int usage_count) {
        this.usage_count = usage_count;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(String last_update_date) {
        this.last_update_date = last_update_date;
    }

    public float getMatch() {
        return match;
    }

    public void setMatch(float match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "Matches{" +
                "id='" + id + '\'' +
                ", segment='" + segment + '\'' +
                ", translation='" + translation + '\'' +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", quality='" + quality + '\'' +
                ", reference='" + reference + '\'' +
                ", usage_count=" + usage_count +
                ", subject='" + subject + '\'' +
                ", created_by='" + created_by + '\'' +
                ", last_updated_by='" + last_updated_by + '\'' +
                ", created='" + created + '\'' +
                ", last_update_date='" + last_update_date + '\'' +
                ", match=" + match +
                '}';
    }
}
