package kr.co.inkstone.common.exception;

public class MemberNotExistException extends RuntimeException {

    public MemberNotExistException(){
        super("존재하지 않는 아이디입니다.");
    }

    public MemberNotExistException(String message){
        super(message);
    }
}
