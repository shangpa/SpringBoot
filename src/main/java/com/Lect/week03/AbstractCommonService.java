package com.Lect.week03;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
// Spring의 의존성 주입과 함께 사용하여 공통 의존성을 설정하거나 관리하는데 유용
public abstract class AbstractCommonService {
    @Autowired //byName 방식으로 주입
    private Animal cat;

    private int defaultValue = 10;
}
