package com.allcheer.bpos.service;



import com.allcheer.bpos.domain.MenuBO;

import java.util.List;

/**
 * Created by APPLE on 16/1/12.
 */
public interface MenuService {
    List<MenuBO> getAllEnabledMenu();

    List<MenuBO> getAllEnabledMenuByUserId(String userid);
}
