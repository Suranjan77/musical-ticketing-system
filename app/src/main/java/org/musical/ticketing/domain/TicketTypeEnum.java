package org.musical.ticketing.domain;

/**
 *
 * @author suranjanpoudel
 */
public enum TicketTypeEnum {
    ADULT("Adult"),
    SENIOR("Senior"),
    STUDENT("Student");
    
    private final String value;
    
    TicketTypeEnum(String val) {
        this.value = val;
    }
    
    public String getValue() {
        return this.value;
    }

}
