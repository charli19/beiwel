package com.beiwel.model;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private String mailTo;
    private String subject;
    private String from;
    private Map<String, Object> data;
    private String template;

    public static Map<String, Object> buildData(String messageNotification, String... link) {
        Map<String, Object> data = new HashMap<>();
        data.put("data", messageNotification);

        if (link.length > 0) {
            data.put("link", link[0]);
        }

        return data;
    }

    public static Map<String, Object> buildCodeData(String messageNotification, String code,
                                                    String footMessage) {
        Map<String, Object> data = new HashMap<>();
        data.put("data", messageNotification);
        data.put("code", code);
        data.put("foot", footMessage);

        return data;
    }

    public static Map<String, Object> buildDataEmailVerification(Notification notification) {
        Map<String, Object> data = new HashMap<>();

        if (StringUtils.isNotBlank(notification.getHeadMessageVerification())) {
            data.put("headMessageVerification", notification.getHeadMessageVerification());
        }
        if (StringUtils.isNotBlank(notification.getLinkVerification())) {
            data.put("linkVerification", notification.getLinkVerification());
        }
        if (StringUtils.isNotBlank(notification.getDownloadMessageVerification())) {
            data.put("downloadMessageVerification", notification.getDownloadMessageVerification());
        }
        if (StringUtils.isNotBlank(notification.getLinkDownload())) {
            data.put("linkDownload", notification.getLinkDownload());
        }
        if (StringUtils.isNotBlank(notification.getInstructionsMessageVerification())) {
            data.put("instructionsMessageVerification",
                    notification.getInstructionsMessageVerification());
        }
        if (StringUtils.isNotBlank(notification.getInstructionsLink())) {
            data.put("instructionsLink", notification.getInstructionsLink());
        }
        if (StringUtils.isNotBlank(notification.getFootMessage())) {
            data.put("footMessage", notification.getFootMessage());
        }
        if (StringUtils.isNotBlank(notification.getFaqMessageVerification())) {
            data.put("faqMessageVerification", notification.getFaqMessageVerification());
        }
        if (StringUtils.isNotBlank(notification.getFaqLink())) {
            data.put("faqLink", notification.getFaqLink());
        }
        if (StringUtils.isNotBlank(notification.getContactMessageVerification())) {
            data.put("contactMessageVerification", notification.getContactMessageVerification());
        }
        if (StringUtils.isNotBlank(notification.getContactLink())) {
            data.put("contactLink", notification.getContactLink());
        }

        return data;
    }


    public static Map<String, Object> buildFullData(
            String presentation,
            String infoCita,
            String address,
            String linkAddress,
            String action,
            String actionButton,
            String linkAction,
            String postAction
            ) {

        Map<String, Object> data = new HashMap<>();
        data.put("presentation", presentation);
        data.put("infoCita", infoCita);
        data.put("address", address);
        data.put("linkAddress", linkAddress);
        data.put("action", action);
        data.put("actionButton", actionButton);
        data.put("linkAction", linkAction);
        data.put("postAction", postAction);

        return data;
    }
}
