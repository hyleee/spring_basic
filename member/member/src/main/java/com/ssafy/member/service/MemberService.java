package com.ssafy.member.service;

import com.ssafy.member.dto.MemberDTO;
import com.ssafy.member.entity.MemberEntity;
import com.ssafy.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 생성자주입
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm(){ return "save";}


    public void save(MemberDTO memberDTO) {
        // repository의 save 메서드 호출 ( 조건: entity 객체를 넘겨주어야 함)
        // 1) dto객체를 entity로 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        // 2) repository의 save 메서드 호출 (jpa 기본제공. 조건: entity 객체로 넘겨주어야 함.)
        memberRepository.save(memberEntity);

    }

    public MemberDTO login(MemberDTO memberDTO) {
        // 1. 회원이 입력한 이메일로 DB에서 조회를 함
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        // 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하느지 판단
        if(byMemberEmail.isPresent()){ // 조회 결과가 있다면 ( 해당 이메일을 가진 회원 정보가 있다면)
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                // 비밀번호 일치
                // entity -> dto로 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;

            } else{
                // 비밀번호 불일치
                return null;
            }

        } else{ // 조회 결과가 없다면
            return null;
        }

    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();

        for(MemberEntity memberEntity: memberEntityList){
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            memberDTOList.add(memberDTO);
        }

        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        // 하나의 객체를 다룰 때, repository는 주로 optional이라는 포장지에 감싸서 전해준다.
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()){
            // optional객체.get() : optional 포장지를 벗기는 작업과 같음.
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else{
            return null;
        }
    }

    public MemberDTO updateForm(String myEmail) {
        // 이메일로 회원 정보 조회 (select * from member_table where member_email = ?)
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if(optionalMemberEntity.isPresent()){
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else{
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        // repository 기본 제공 save 메서드 특징
        // 1) 인자에 id가 없으면 insert 쿼리를 수행
        // 2) DB 내 id가 있는 entity 객체가 인자로 넘어올 경우, update query 실행
        // update member_table set member_email=?,member_name=?,member_password=? where id=?

        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }
}
