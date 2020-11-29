package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {
    private LocalDate holidayDate;
    private String holidayName;
    private Boolean customDate;
    private LocalDateTime regTime;
}
