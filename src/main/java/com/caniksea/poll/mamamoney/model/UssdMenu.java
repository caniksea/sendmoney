package com.caniksea.poll.mamamoney.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UssdMenu {

    @Id
    private String menuNumber;
    private String menuDescription;

    private UssdMenu(Builder builder) {
        this.menuNumber = builder.menuNumber;
        this.menuDescription = builder.menuDescription;
    }

    protected UssdMenu() {}

    public String getMenuNumber() {
        return menuNumber;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    @Override
    public String toString() {
        return "UssdMenu{" +
                "menuNumber='" + menuNumber + '\'' +
                ", menuDescription='" + menuDescription + '\'' +
                '}';
    }

    public static class Builder {
        private String menuNumber, menuDescription;

        public Builder menuNumber(String menuNumber) {
            this.menuNumber = menuNumber;
            return this;
        }

        public Builder menuDescription(String menuDescription) {
            this.menuDescription = menuDescription;
            return this;
        }

        public UssdMenu build() {
            return new UssdMenu(this);
        }
    }
}
