package com.mailbox.enums;

public enum MailType {
    SOCIAL,
    MALWARE,
    ATTACHMENTS,
    PROMOTION,
    MUSIC,
    UNKNOWN;

    public static MailType convert(String step) {
        switch(step) {
            case "social":
                return MailType.SOCIAL;
            case "malware":
                return MailType.MALWARE;
            case "attachments":
                return MailType.ATTACHMENTS;
            case "promotion":
                return MailType.PROMOTION;
            case "music":
                return MailType.MUSIC;
            default:
                return MailType.UNKNOWN;
        }
    }
}
