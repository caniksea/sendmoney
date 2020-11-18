package com.caniksea.poll.mamamoney.model;

public class UssdResponse {

    private String sessionId, message;

    protected UssdResponse() {}

    private UssdResponse(Builder builder) {
        this.sessionId = builder.sessionId;
        this.message = builder.message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {
        private String sessionId, message;

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public UssdResponse build() {
            return new UssdResponse(this);
        }
    }

    @Override
    public String toString() {
        return "UssdResponse{" +
                "sessionId='" + sessionId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
