package com.Lect.week03;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Getter
@Setter
public class CommonService extends AbstractCommonService{
    private int periodTime;
}
