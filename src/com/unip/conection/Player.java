package com.unip.conection;

public class Player {

    private String nickname;
    private String password;
    private String security_question;
    private String security_answer;    
    
    public Player(String nickname, String password, String security_question, String security_answer) {
        
        this.nickname = nickname;
        this.password = password;
        this.security_question = security_question;
        this.security_answer = security_answer;
    }
    
    public Player(String nickname){
        this.nickname = nickname;
    }
    
    public Player(){
        
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return security_question;
    }

    public void setSecurityQuestion(String security_question) {
        this.security_question = security_question;
    }

    public String getSecurityAnswer() {
        return security_answer;
    }

    public void setSecurityAnswer(String security_answer) {
        this.security_answer = security_answer;
    }

}
