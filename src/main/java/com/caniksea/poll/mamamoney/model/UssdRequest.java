package com.caniksea.poll.mamamoney.model;

public class UssdRequest {

    private String sessionId, msisdn, userEntry;

    private UssdRequest(Builder builder) {
        this.sessionId = builder.sessionId;
        this.msisdn = builder.msisdn;
        this.userEntry = builder.userEntry;
    }

    protected UssdRequest() {}

    public String getSessionId() {
        return sessionId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public String getUserEntry() {
        return userEntry;
    }

    public static class Builder {
        private String sessionId, msisdn, userEntry;

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder msisdn(String msisdn) {
            this.msisdn = msisdn;
            return this;
        }

        public Builder userEntry(String userEntry) {
            this.userEntry = userEntry;
            return this;
        }

        public UssdRequest build() {
            return new UssdRequest(this);
        }
    }

    @Override
    public String toString() {
        return "UssdRequest{" +
                "sessionId='" + sessionId + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", userEntry='" + userEntry + '\'' +
                '}';
    }
}
