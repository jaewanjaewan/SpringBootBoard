package com.koreait.springbootboard.user;

import com.koreait.springbootboard.MyUserUtils;
import com.koreait.springbootboard.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired private UserMapper mapper;
    @Autowired private MyUserUtils userUtils;

    // 1: 로그인 성공, 2: 아이디 없음, 3: 비밀번호 다름
    public int login(UserEntity entity){
        UserEntity dbUser = mapper.selUser(entity);
        if(dbUser == null){
            return 2;
        } else if(BCrypt.checkpw(entity.getUpw(), dbUser.getUpw())) {
            //서버의 메모리 부담을 덜어주기위해 null로해준다
            dbUser.setUpw(null);
            dbUser.setRdt(null);
            dbUser.setMdt(null);
            userUtils.setLoginUser(dbUser);
            return 1;
        } else {
            return 3;
        }
    }

    public int join(UserEntity entity){
        String hashedUpw = BCrypt.hashpw(entity.getUpw(), BCrypt.gensalt());
        entity.setUpw(hashedUpw);
        try {
            return mapper.insUser(entity);
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
