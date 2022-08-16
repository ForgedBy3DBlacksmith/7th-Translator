package com.seventh.translate.mymemory;

import java.util.List;
public class MyMemoryResponse {
    private ResponseData responseData;
    private boolean quotaFinished;
    private String responseDetails;
    private int responseStatus;
    private String responderId;
    private String exception_code;
    private List<Matches> matches;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public String getResponseDetails() {
        return responseDetails;
    }

    public void setResponseDetails(String responseDetails) {
        this.responseDetails = responseDetails;
    }

    public boolean isQuotaFinished() {
        return quotaFinished;
    }

    public void setQuotaFinished(boolean quotaFinished) {
        this.quotaFinished = quotaFinished;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponderId() {
        return responderId;
    }

    public void setResponderId(String responderId) {
        this.responderId = responderId;
    }

    public String getException_code() {
        return exception_code;
    }

    public void setException_code(String exception_code) {
        this.exception_code = exception_code;
    }

    public List<Matches> getMatches() {
        return matches;
    }

    public void setMatches(List<Matches> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "MyMemoryResponse{" +
                "responseData=" + responseData +
                ", quotaFinished=" + quotaFinished +
                ", responseDetails='" + responseDetails + '\'' +
                ", responseStatus=" + responseStatus +
                ", responderId='" + responderId + '\'' +
                ", exception_code='" + exception_code + '\'' +
                ", matches=" + matches +
                '}';
    }
}
