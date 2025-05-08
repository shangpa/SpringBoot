package com.Lect.week05;

import org.springframework.stereotype.Service;

@Service
public class ExAOPService {
    //Ex1
    public void performSensitiveOperation(String msg, int cnt) {
        System.out.println("민감한 작업 실행중 ... ");
    }
    public String placeOrder(Double orderID) throws SecurityException {
        double random = Math.random();
        if (random > orderID)
            throw new SecurityException(random+ " : " + orderID +"[조건불일치]");
        return random + " : " + orderID+"[조건일치]";
    }
    public String check(String userID, String role){
        if(!"Admin".equals(userID.toUpperCase())){
            return "접근권한이없습니다.";
        }
    return "접근권한있어요";
    }
}
