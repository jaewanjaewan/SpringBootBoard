package com.koreait.springbootboard;

import com.koreait.springbootboard.board.model.BoardCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component //개발자가 직접 작성한 class를 bean으로 등록하기위한 어노테이션
public class MyMenus {

    @Autowired private HomeService service;

    private List<BoardCategoryEntity> menusList;

    @Bean("menus")
    public List<BoardCategoryEntity> getMenus(){
        if(menusList == null){
            menusList = service.selMenuCategoryList();
        }
        return menusList;
    }
}
