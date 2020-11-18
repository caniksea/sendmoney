package com.caniksea.poll.mamamoney.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Currency {

    @Id
    private String id;
    private String code, codeDescription, countryName;
    @Column(columnDefinition = "decimal(19,5)")
    private BigDecimal rate;

    private Currency(Builder builder) {
        this.id = builder.id;
        this.code = builder.code;
        this.codeDescription = builder.codeDescription;
        this.countryName = builder.countryName;
        this.rate = builder.rate;
    }

    protected Currency() {}

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public String getCountryName() {
        return countryName;
    }

    public static class Builder {
        private String id, code, codeDescription, countryName;
        private BigDecimal rate;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder codeDescription(String codeDescription) {
            this.codeDescription = codeDescription;
            return this;
        }

        public Builder countryName(String countryName) {
            this.countryName = countryName;
            return this;
        }

        public Builder rate(BigDecimal rate) {
            this.rate = rate;
            return this;
        }

        public Currency build() {
            return new Currency(this);
        }
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", codeDescription='" + codeDescription + '\'' +
                ", countryName='" + countryName + '\'' +
                ", rate=" + rate +
                '}';
    }
}
