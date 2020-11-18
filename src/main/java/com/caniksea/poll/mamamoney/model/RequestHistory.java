package com.caniksea.poll.mamamoney.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@IdClass(RequestHistory.RequestHistoryId.class)
public class RequestHistory {

    @Id
    private String msisdn, sessionId, menuNumber;
    private String choice;
    private LocalDateTime dateTime;

    private RequestHistory(Builder builder) {
        this.msisdn = builder.msisdn;
        this.sessionId = builder.sessionId;
        this.menuNumber = builder.menuNumber;
        this.choice = builder.choice;
        this.dateTime = builder.dateTime;
    }

    protected RequestHistory() {}

    public String getMsisdn() {
        return msisdn;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getMenuNumber() {
        return menuNumber;
    }

    public String getChoice() {
        return choice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "RequestHistory{" +
                "msisdn='" + msisdn + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", menuNumber='" + menuNumber + '\'' +
                ", choice='" + choice + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    public static class Builder {
        private String msisdn, sessionId, menuNumber, choice;
        private LocalDateTime dateTime;

        public Builder msisdn(String msisdn) {
            this.msisdn = msisdn;
            return this;
        }

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder menuNumber(String menuNumber) {
            this.menuNumber = menuNumber;
            return this;
        }

        public Builder choice(String choice) {
            this.choice = choice;
            return this;
        }
        public Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public RequestHistory build() {
            return new RequestHistory(this);
        }
    }

    public static Set<RequestHistory> sortByDateAsc(final Set<RequestHistory> requestHistories) {
        Comparator<RequestHistory> dateComparator = (a, b) -> b.getDateTime().compareTo(a.getDateTime());
        return requestHistories.stream().sorted(dateComparator).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static class RequestHistoryId implements Serializable {
        private String msisdn, sessionId, menuNumber;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RequestHistoryId that = (RequestHistoryId) o;
            return msisdn.equals(that.msisdn) &&
                    sessionId.equals(that.sessionId) &&
                    menuNumber.equals(that.menuNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(msisdn, sessionId, menuNumber);
        }
    }
}
