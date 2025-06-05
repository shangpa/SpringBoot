package com.Lect.week09;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
// final 필드만 매개변수로 갖는 생성자 자동생성
@RequiredArgsConstructor
public class Question {
    private final String title;
    private List<String> options;

    public boolean isChoice(){
        return options != null && !options.isEmpty();
    }
}
