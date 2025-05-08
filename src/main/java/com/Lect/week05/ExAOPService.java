package com.Lect.week05;

import org.springframework.stereotype.Service;

@Service
public class ExAOPService {
    //Ex1
    public void performSensitiveOperation(String msg, int cnt) {
        System.out.println("민감한 작업 실행중 ... ");
    }
}
