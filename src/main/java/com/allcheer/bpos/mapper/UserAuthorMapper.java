package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBTSSysUsrVO;

/**
 * Created by fireWorks on 2016/2/26.
 */
public interface UserAuthorMapper {
    TblBTSSysUsrVO getAuthorByUserId(String userId);
}
