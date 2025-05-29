package com.Lect.week06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepos = null;

    public List<Member> getMembers() {
        String sql = "select * from MEMBER";
        return memberRepos.selectAll(sql);
    }
    public List<Member> getMembersUsingParameter() {
        String sql = "select * from MEMBER where email = ? and NAME=?";
        Object[] args = {"virus1@virus.net","std1"};
        return memberRepos.selectAllUsingParameter(sql, args);
    }
    public List<Map<String, Member>> getMembersUsingMap() {
        // SQL: 이메일이 "virus1@virus.net"인 회원들의 id, name, password, regdate를 조회
        String sql = "select id,name,password,regdate from MEMBER where email = ?";

        // SQL 실행 결과는 각 행이 Map<String, Object> 형태로 구성된 리스트
        List<Map<String, Object>> rows = memberRepos.selectListMap(sql, "virus1@virus.net");
        System.out.println("로그로그"+rows);
        // 결과로 반환할 리스트 (각 요소는 id를 key로 하고 Member 객체를 value로 하는 Map)
        List<Map<String, Member>> result = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            // 각 row는 DB에서 가져온 한 명의 회원 정보 (컬럼명 -> 값)

            // Member 객체 생성
            // 주의: 생성자의 첫 번째 인자는 email이므로, 현재 null이 들어가면 email이 빠지게 됨
            Member member = new Member(//email 조회를 안해서 null 넣은듯
                    null, // email 자리인데 null이 들어감 → 아래에서 수정
                    (String) row.get("PASSWORD"), // 두 번째: password
                    (String) row.get("NAME"),     // 세 번째: name
                    (LocalDateTime) row.get("REGDATE") // 네 번째: registerDateTime
            );
            System.out.println("로그로그"+member);

            // email이 아닌 id 값을 키로 사용하여 Map 생성
            Map<String, Member> map = new HashMap<>();
            map.put(row.get("id").toString(), member); // id는 Long → 문자열로 변환하여 key로 사용

            // 만든 Map을 리스트에 추가
            result.add(map);
            System.out.println("로그로그"+map);
        }

        // 최종적으로 List<Map<String, Member>> 반환
        System.out.println("로그로그123"+result);
        return result;
    }

    public List<Member> getUpdateMembers() {
        String sql = "update MEMBER set NAME=?,PASSWORD=? where EMAIL=?";
        Object[] args1 = {"stdVirus","2345","virus1@virus.net"};
        memberRepos.updateMember(sql,args1);

        sql = "select * from MEMBER where email=?";//간단한 select 쿼리문
        Object[] args2 = {"virus1@virus.net"};
        return memberRepos.selectAllUsingParameter(sql, args2);
    }

    public List<Member> getBatchUpdateArray(){
        String sql =
                "insert into MEMBER(EMAIL,PASSWORD,NAME,REGDATE) values(?,?,?,?)";
        //데이터 배열 준비
        List<Object[]> memberData = Arrays.asList(
                new Object[]{"john@example.com","2456","John",LocalDateTime.now()},
                new Object[]{"jane@example.com","34567","Jane",LocalDateTime.now()},
                new Object[]{"mike@example.com","4567","Mike",LocalDateTime.now()}
        );
        memberRepos.batchInsertMembers(sql, memberData);
        return  getMembers();
    }
    public List<Member> getBatchUpdateSetter(){
        String sql =
                "insert into MEMBER(EMAIL,PASSWORD,NAME,REGDATE) values(?,?,?,?)";
        //member 객체 준비
        List<Member> memberData = Arrays.asList(
                new Member("johnSetter@example.com","2456","JohnSetter",LocalDateTime.now()),
                new Member("janeSetter@example.com","34567","JaneSetter",LocalDateTime.now()),
                new Member("mikeSetter@example.com","4567","MikeSetter",LocalDateTime.now())
        );
        memberRepos.batchInsertMembersSetter(sql, memberData);
        return  getMembers();
    }

    public List<Member> getKeyHolder() {
        // DB에 새로운 Member를 insert하면서, 자동 생성된 id 값을 받아와서 출력하고,
        // 전체 회원 목록을 조회해서 반환하는 메서드

        String sql = "insert into MEMBER (EMAIL,PASSWORD,NAME,REGDATE) values(?,?,?,?)";
        // 실행할 insert 쿼리 작성. (파라미터로 물음표 ?를 사용해서 값 바인딩)

        Member member = new Member("virus1@virus.com", "3456", "홍길동", LocalDateTime.now());

        long KeyHolder = memberRepos.insertMember(member,sql);
        // 위에서 만든 member를 DB에 저장하고, 자동 생성된 id 값을 받아온다.
        // memberRepos는 insertMember 함수를 가진 저장소(Repository) 객체

        System.out.println("자동 생성된 값 :" + KeyHolder);
        // DB에서 생성된 id(PK 값)를 콘솔에 출력

        return getMembers();
        // 전체 회원 목록을 다시 조회해서 반환
    }

    public List<Member> transactionProcess() {
        // 트랜잭션(rollback 테스트 포함) 처리 후, 전체 회원 목록을 반환하는 메서드

        try {
            Member member = new Member("홍길동@virus.com", "1234", "홍길동", LocalDateTime.now());
            // 새로 추가할 Member 객체 생성 (이메일, 비번, 이름, 등록시간)

            memberRepos.changePassword(member, "5678");
            // changePassword 메서드 실행:
            // 1. member를 insert
            // 2. 비밀번호/이름 update
            // (여기서 일부러 쿼리 오타로 롤백 발생 가능!)
        } catch (Exception e) {
            System.out.println("Transaction rolled back" + e.getMessage());
            // 만약 changePassword 과정에서 Exception 발생하면
            // "Transaction rolled back" 메시지와 에러 원인 출력
            // (실제로 쿼리 오타 때문에 rollback 일어남)
        }

        String sql = "select * from MEMBER";
        // 전체 회원 데이터를 조회할 select 쿼리문 준비

        return memberRepos.selectAll(sql);
        // 현재 MEMBER 테이블의 모든 데이터를 조회해서 반환
    }


}
