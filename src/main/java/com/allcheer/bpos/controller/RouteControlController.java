package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblRouteControl;
import com.allcheer.bpos.entity.TblRouteControlExample;
import com.allcheer.bpos.form.RouteControlForm;
import com.allcheer.bpos.mapper.TblRouteControlMapper;
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
@RequestMapping("route")
public class RouteControlController {

    @Autowired
    private TblRouteControlMapper tblRouteControlMapper;

    @RequiresAuthentication
    @RequestMapping(value = "gopage")
    public String goToLoginPage(HttpServletRequest request) {
        RouteControlForm routeControlForm = new RouteControlForm();

        int pageCurrent = 1;
        int pageSize = 100;

        TblRouteControlExample tblRouteControlExample = new TblRouteControlExample();
        tblRouteControlExample.createCriteria();
        int transSize = tblRouteControlMapper.countByExample(tblRouteControlExample);

        Pagination<TblRouteControl> pagination = new Pagination<TblRouteControl>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblRouteControl> tblRouteControlList = tblRouteControlMapper.selectByExample(tblRouteControlExample);
        pagination.addResult(tblRouteControlList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("route", routeControlForm);

        return "route/pos_route";
    }

    @RequestMapping("search")
    @RequiresAuthentication
    public String doSelect(@ModelAttribute("route") RouteControlForm route, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) {
        String instId = null;
        String merId = null;
        String termId = null;

        if (!StringUtils.isBlank(route.getInstId())) {
            instId = route.getInstId();
        }

        if (!StringUtils.isBlank(route.getMerId())) {
            merId = route.getMerId();
        }

        if (!StringUtils.isBlank(route.getTermId())) {
            termId = route.getTermId();
        }

        int pageCurrent = Integer.parseInt(route.getPageCurrent());
        int pageSize = Integer.parseInt(route.getPageSize());

        int transSize = tblRouteControlMapper.countByCust(instId, merId, termId);

        Pagination<TblRouteControl> pagination = new Pagination<TblRouteControl>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblRouteControl> tblRouteControlList = tblRouteControlMapper.selectByCust(instId, merId, termId);
        pagination.addResult(tblRouteControlList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("route", route);

        return "route/pos_route";
    }

}
