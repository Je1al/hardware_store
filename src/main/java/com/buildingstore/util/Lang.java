package com.buildingstore.util;

/**
 * Язык интерфейса. По умолчанию — русский (RU), доступно переключение
 * на английский (EN). Выбор сохраняется в cookie.
 */
public enum Lang {

    RU("ru"),
    EN("en");

    private final String code;

    Lang(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /** Разбор кода языка; при ошибке — русский. */
    public static Lang from(String code) {
        if (code != null) {
            for (Lang l : values()) {
                if (l.code.equalsIgnoreCase(code.trim())) {
                    return l;
                }
            }
        }
        return RU;
    }
}
