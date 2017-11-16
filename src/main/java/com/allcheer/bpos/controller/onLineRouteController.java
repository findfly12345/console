package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblOnlineRoute;
import com.allcheer.bpos.entity.TblOnlineRouteExample;
import com.allcheer.bpos.form.OnlineRouteControlForm;
import com.allcheer.bpos.mapper.TblOnlineRouteMapper;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("onlineRoute")
public class onLineRouteController {

    @Autowired
    private TblOnlineRouteMapper tblOnlineRouteMapper;

    @RequiresAuthentication
    @RequestMapping(value = "gopage")
    public String goToLoginPage(HttpServletRequest request) {
        OnlineRouteControlForm onlineRouteControlForm = new OnlineRouteControlForm();

        int pageCurrent = 1;
        int pageSize = 100;

        TblOnlineRouteExample tblRouteControlExample = new TblOnlineRouteExample();
        tblRouteControlExample.createCriteria();
        int transSize = tblOnlineRouteMapper.countByExample(tblRouteControlExample);

        Pagination<TblOnlineRoute> pagination = new Pagination<TblOnlineRoute>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblOnlineRoute> tblRouteControlList = tblOnlineRouteMapper.selectByExample(tblRouteControlExample);
        pagination.addResult(tblRouteControlList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("onlineRoute", onlineRouteControlForm);

        return "route/online_route";
    }

    @RequestMapping("search")
    @RequiresAuthentication
    public String doSelect(@ModelAttribute("onlineRoute") OnlineRouteControlForm onlineRoute, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) {
        String instId = null;
        String merId = null;

        if (!StringUtils.isBlank(onlineRoute.getInstId())) {
            instId = onlineRoute.getInstId();
        }

        if (!StringUtils.isBlank(onlineRoute.getMerId())) {
            merId = onlineRoute.getMerId();
        }


        int pageCurrent = Integer.parseInt(onlineRoute.getPageCurrent());
        int pageSize = Integer.parseInt(onlineRoute.getPageSize());

        int transSize = tblOnlineRouteMapper.countByCust(instId, merId);

        Pagination<TblOnlineRoute> pagination = new Pagination<TblOnlineRoute>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblOnlineRoute> tblRouteControlList = tblOnlineRouteMapper.selectByCust(instId, merId);
        pagination.addResult(tblRouteControlList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("onlineRoute", onlineRoute);

        return "route/online_route";
    }

}
