package com.Lect.week03;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class LombokService {
    private final SmsSender sms;
    private final int num;

    private String msg;
    private WorkUnit unit;

}
