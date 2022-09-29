package org.example;

public class User {
    private String password;

    public void initPassword(PasswordGenerator passwordGenerator){
        // as-is 방식
        // 내부에서 생성하는 방식은 강한 결합도를 가지게 됩니다.
        /**
         * RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
         * String password = randomPasswordGenerator.generatePassword();
        */

        // to-be 방식
        String password = passwordGenerator.generatePassword();

        /**
         * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
         */
        if(password.length() >= 8 && password.length() <= 12){
            this.password = password;
        }


    }

    public String getPassword() {
        return password;
    }
}
