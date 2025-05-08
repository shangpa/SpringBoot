package com.Lect.week05;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Aspect
@Component
public class AdviceAspect {
    //특정 패키지의 메서드 실행 전에 인증 처리
    @Before("execution(* com.Lect.week05.ExAOPService.performSensitiveOperation(..))")
    public void authenticate(JoinPoint joinPoint) {
        //실행되는 메서드 이름 가져오기
        String methodName = joinPoint.getSignature().getName();
        //메서드의 인수 가져오기
        Object[] args = joinPoint.getArgs();

        System.out.println("[Auth] 메서드 실행전 - 메서드 이름 : " + methodName);
        System.out.println("[Auth] 메서드 실행전 - 인수 : " + java.util.Arrays.toString(args));

        //인증 로직 (예 : 현재 사용자가 인증되었는지 확인)
        boolean isAuthenticated = checkAuthentication();
        if(!isAuthenticated) {
            //SecurityException 클래스는 Java의 표준 예외 클래스 중 하나로, 보안관련 문제가 발생했을때 이를
            throw new SecurityException("사용자가 인증되지 않았습니다.");
        }
        System.out.println("[Auth] 인증성공 : 사용자가 메서드 실행을 허용 받았음");
    }
    private boolean checkAuthentication(){
        double value = Math.random();
        return value > 0.5;
    }

    @Pointcut("execution(* com.Lect..*(..))")
    private void pointcutEXP() {}
    @AfterReturning(pointcut = "pointcutEXP()",returning = "result")
    public void LogAfterReturning(Object result) {
        System.out.println("[Log] 메서드가 정상적으로 종료되엇습니다.");
        System.out.println("[Log] 반환값 : " + result);
    }
    @AfterThrowing(pointcut = "pointcutEXP()",throwing = "authException")
    public void exceptionProcess(Throwable authException) {
        System.out.println("================");
        System.out.println("예외발생!!, 예외= "+authException.getMessage());
    }
    @After("pointcutEXP()")
    public void LogAfter() {
        System.out.println("[INFO] 메서드 실행 후 처리되었습니다");
    }

    @Around("execution(* com.Lect.week05.ExAOPService.check(..))")
    public Object checkPermission(ProceedingJoinPoint joinPoint)throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        System.out.println("[Around] 시작 시간 : "+startTime);

        Scanner in = new Scanner(System.in);
        System.out.print("사용자 권한 : ");

        args[0] = in.nextLine();
        Object result = joinPoint.proceed(args);//대상메서드 실행, 매개변수값을 변경

        long endTime = System.currentTimeMillis();
        System.out.println("[Around] 메서드 실행 시간 : "+(endTime-startTime)+"ms");

        String methodName = joinPoint.getSignature().getName();
        result= "["+methodName+"] 메서드에 대한"+result;
        return result;


    }
}
