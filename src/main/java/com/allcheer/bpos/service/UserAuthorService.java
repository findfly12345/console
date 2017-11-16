package com.allcheer.bpos.service;


import com.allcheer.bpos.domain.UserBO;

/**
 * Created by APPLE on 16/1/13.
 */
public interface UserAuthorService {
    UserBO getAuthorByUserId(String userId);
}
