package com.caniksea.poll.mamamoney.controller;

import com.caniksea.poll.mamamoney.factory.RequestHistoryFactory;
import com.caniksea.poll.mamamoney.factory.UssdResponseFactory;
import com.caniksea.poll.mamamoney.model.*;
import com.caniksea.poll.mamamoney.service.CurrencyService;
import com.caniksea.poll.mamamoney.service.RequestHistoryService;
import com.caniksea.poll.mamamoney.service.UssdMenuService;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@RestController
public class RequestController {

    private final Logger logger = LoggerFactory.getLogger(RequestController.class);
    @Autowired
    private RequestHistoryService requestHistoryService;
    @Autowired
    private UssdMenuService ussdMenuService;
    @Autowired
    private CurrencyService currencyService;

    /**
     * Process initial request (no record of sessionid exists)
     *
     * @param ussdRequest
     * @return
     */
    private UssdResponse processInitial(UssdRequest ussdRequest) {
        UssdResponse ussdResponse = UssdResponseFactory.build(ussdRequest.getSessionId(), "An error occurred; initial menu not found!");
        UssdMenu ussdMenu = getUssdMenu("1");
        if (ussdMenu != null) {
            RequestHistory requestHistory = RequestHistoryFactory.build(ussdRequest.getMsisdn(), ussdRequest.getSessionId(), "0", "Show menu.",
                    true, "Success");
            requestHistoryService.create(requestHistory);
            ussdResponse = UssdResponseFactory.build(ussdRequest.getSessionId(), ussdMenu.getMenuDescription());
        }
        return ussdResponse;
    }

    /**
     * Handles subsequent request after initial request.
     *
     * @param ussdRequest
     * @param latestRequest
     * @return
     */
    private UssdResponse processSubsequent(UssdRequest ussdRequest, RequestHistory latestRequest) {
        UssdResponse ussdResponse = null;
        int previousMenu = Integer.parseInt(latestRequest.getMenuNumber());
        int currentMenu = previousMenu + 1;
        int nextMenu = currentMenu + 1;
        int maxMenu = ussdMenuService.findAll().size();
        String userEntry = ussdRequest.getUserEntry() == null ? "" : ussdRequest.getUserEntry().trim();
        if (StringHelper.isEmptyOrWhiteSpace(userEntry))
            ussdResponse = UssdResponseFactory.build(ussdRequest.getSessionId(), "No selection!");
        else {
            if (currentMenu < maxMenu) {
                if (currentMenu == 1) {
                    ussdResponse = processFirstMenu(ussdRequest, userEntry, currentMenu, nextMenu);
                } else if (currentMenu == 2) {
                    ussdResponse = processSecondMenu(ussdRequest, latestRequest, userEntry, currentMenu, nextMenu);
                } else if (currentMenu == 3) {
                    ussdResponse = performThirdMenu(ussdRequest, latestRequest, userEntry, currentMenu);
                }
            } else ussdResponse = UssdResponseFactory.build(ussdRequest.getSessionId(), "Session expired!");
        }
        return ussdResponse;
    }

    /**
     * Handles menu 3
     *
     * @param ussdRequest
     * @param latestRequest
     * @param userEntry
     * @param currentMenu
     * @return
     */
    private UssdResponse performThirdMenu(UssdRequest ussdRequest, RequestHistory latestRequest, String userEntry, int currentMenu) {
        String message = "An error occurred; ";
        boolean isSuccess = false;
        String comment;
        String choice = userEntry;
        UssdMenu currentMenuObj = getUssdMenu(currentMenu + "");
        if (userEntry.equals("1")) {
            message = "Thank you for using Mama Money!";
            isSuccess = true;
            comment = "Success";
            choice = latestRequest.getChoice().split(";")[1];
        } else {
            comment = "Invalid option selected!";
            message += comment;
        }
        RequestHistory requestHistory = RequestHistoryFactory.build(ussdRequest.getMsisdn(), ussdRequest.getSessionId(), currentMenuObj.getMenuNumber(), choice,
                isSuccess, comment);
        requestHistoryService.create(requestHistory);
        return UssdResponseFactory.build(ussdRequest.getSessionId(), message);
    }

    /**
     * Handles menu 2.
     *
     * @param ussdRequest
     * @param latestRequest
     * @param userEntry
     * @param currentMenu
     * @param nextMenu
     * @return
     */
    private UssdResponse processSecondMenu(UssdRequest ussdRequest, RequestHistory latestRequest, String userEntry, int currentMenu, int nextMenu) {
        String message = "An error occurred; ";
        boolean isSuccess = false;
        String comment;
        String currencyId = latestRequest.getChoice();
        String choice = "ZAR" + userEntry;
        UssdMenu currentMenuObj = getUssdMenu(currentMenu + "");
        Currency currency = currencyService.read(currencyId).orElseGet(null);
        if (NumberUtils.isParsable(userEntry)) {
            BigDecimal amount = new BigDecimal(userEntry);
            BigDecimal converted = currency.getRate().multiply(amount).setScale(2, RoundingMode.HALF_EVEN);
            choice += ";" + currency.getCode() + converted.toPlainString();
            UssdMenu nextMenuObj = getUssdMenu(nextMenu + "");
            message = String.format(nextMenuObj.getMenuDescription(), converted.toPlainString(), currency.getCode());
            isSuccess = true;
            comment = "Success";
        } else {
            comment = "Invalid amount: " + userEntry;
            message += comment;
        }
        RequestHistory requestHistory = RequestHistoryFactory.build(ussdRequest.getMsisdn(), ussdRequest.getSessionId(), currentMenuObj.getMenuNumber(), choice,
                isSuccess, comment);
        requestHistoryService.create(requestHistory);
        return UssdResponseFactory.build(ussdRequest.getSessionId(), message);
    }

    /**
     * Handles menu 1.
     *
     * @param ussdRequest
     * @param userEntry
     * @param currentMenu
     * @param nextMenu
     * @return
     */
    private UssdResponse processFirstMenu(UssdRequest ussdRequest, String userEntry, int currentMenu, int nextMenu) {
        String message = "An error occurred; ";
        boolean isSuccess = false;
        String comment;
        UssdMenu currentMenuObj = getUssdMenu(currentMenu + "");
        if (userEntry.equals("1") || userEntry.equals("2")) {
            Currency currency = currencyService.read(userEntry).orElse(null);
            if (currency != null) {
                isSuccess = true;
                comment = "Success";
                UssdMenu nextMenuObj = getUssdMenu(nextMenu + "");
                message = String.format(nextMenuObj.getMenuDescription(), currency.getCountryName());
            } else {
                comment = "No country/currency found!";
                message += comment;
            }
        } else {
            comment = "Invalid option selected";
            message += comment;
        }
        RequestHistory requestHistory = RequestHistoryFactory.build(ussdRequest.getMsisdn(), ussdRequest.getSessionId(), currentMenuObj.getMenuNumber(),
                userEntry, isSuccess, comment);
        requestHistoryService.create(requestHistory);
        return UssdResponseFactory.build(ussdRequest.getSessionId(), message);
    }

    /**
     * Get ussd menu if exists.
     *
     * @param menuNumber
     * @return
     */
    private UssdMenu getUssdMenu(String menuNumber) {
        return ussdMenuService.read(menuNumber).orElse(null);
    }

    @PostMapping("/ussd")
    public ResponseEntity<UssdResponse> request(@RequestBody UssdRequest ussdRequest) {
        logger.info("Request: " + ussdRequest);
        UssdResponse ussdResponse;
        if (StringHelper.isEmptyOrWhiteSpace(ussdRequest.getSessionId()))
            ussdResponse = UssdResponseFactory.build(ussdRequest.getSessionId(), "SessionId is required!");
        else if (StringHelper.isEmptyOrWhiteSpace(ussdRequest.getMsisdn()))
            ussdResponse = UssdResponseFactory.build(ussdRequest.getSessionId(), "MSISDN is required");
        else {
            Optional<RequestHistory> optionalRequestHistory = requestHistoryService.getLatest(ussdRequest.getMsisdn(), ussdRequest.getSessionId());
            if (optionalRequestHistory.isPresent()) {
                RequestHistory latestRequest = optionalRequestHistory.get();
                if (latestRequest.isSuccess())
                    ussdResponse = processSubsequent(ussdRequest, latestRequest);
                else ussdResponse = UssdResponseFactory.build(ussdRequest.getSessionId(), "Invalid session.");
            } else {
                logger.info("No request history; assuming initial request.");
                ussdResponse = processInitial(ussdRequest);
            }
        }
        return ResponseEntity.ok(ussdResponse);
    }
}
