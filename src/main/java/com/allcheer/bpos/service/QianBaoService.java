package com.allcheer.bpos.service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

/**
 * Created by APPLE on 2016/10/23.
 */
public interface QianBaoService {
    void downChannelPreFile(Map map, HttpServletResponse httpServletResponse);

    Map resultUpdate(File resultFile,String userName);
}
