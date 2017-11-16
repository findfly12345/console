package com.allcheer.bpos.controller;

import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.entity.TblBtsInstRouteDO;
import com.allcheer.bpos.form.InstRouteForm;
import com.allcheer.bpos.service.InstRouteService;
import com.allcheer.bpos.util.*;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("instrout")
public class InstRoutController {

	private Logger logger = LoggerFactory.getLogger(InstRoutController.class);
	@Autowired
	InstRouteService instRouteService;

	@RequestMapping("page")
	@RequiresAuthentication
	public String doPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		InstRouteForm rout = new InstRouteForm();

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = instRouteService.countByExample(rout);

		Pagination<TblBtsInstRouteDO> page = new Pagination<TblBtsInstRouteDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBtsInstRouteDO> userList = instRouteService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);
		request.setAttribute("rout", rout);

		return "instroute/routPage";
	}

	@RequestMapping("search")
	@RequiresAuthentication
	public String doSelect(@ModelAttribute("rout") InstRouteForm rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		int pageCurrent = Integer.parseInt(rout.getPageCurrent());
		int pageSize = Integer.parseInt(rout.getPageSize());
		int userSize = instRouteService.countByExample(rout);

		Pagination<TblBtsInstRouteDO> page = new Pagination<TblBtsInstRouteDO>(userSize, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblBtsInstRouteDO> userList = instRouteService.selectByExample(rout);
		page.addResult(userList);
		request.setAttribute("pageUser", page);

		return "instroute/routPage";
	}

	@RequestMapping("addpage")
	@RequiresAuthentication
	public String doAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		TblBtsInstRouteDO rout = new TblBtsInstRouteDO();
		request.setAttribute("rout", rout);
		return "instroute/addPage";
	}

	@RequestMapping("batchaddpage")
	@RequiresAuthentication
	public String doBatchAddUserPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		return "instroute/batchAddPage";
	}

	@RequestMapping("downloadtemp")
	@RequiresAuthentication
	public String downloadTemp(HttpServletRequest request, HttpServletResponse response)
			throws IOException, BposException {

		String tmpfile = SystemConstant.BATCHUP_TEMPLATE_FILE;
		File file = new File(tmpfile);
		if (!file.exists()) {
			logger.error("模板路径：【{}】", tmpfile);
			throw new BposException("路由模板不存在");
		}

		OutputStream os = response.getOutputStream();
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
			response.setContentType("application/octet-stream; charset=utf-8");
			os.write(FileUtils.readFileToByteArray(file));
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}

		return null;
	}

	@RequestMapping("/uploader")
	@RequiresAuthentication
	public String uploader(MultipartFile file, HttpSession session ,HttpServletResponse response) throws BposException {
		String filename = file.getOriginalFilename();
		logger.debug("接收到文件：{}" + filename);
		if (file.isEmpty()) {
			logger.error("文件接收失败：【{}】", filename);
			throw new BposException("文件是空的");
		}
		
		int acceptLen = 0;
		int saveLen = 0;
		List<TblBtsInstRouteDO> data = new ArrayList<TblBtsInstRouteDO>();
		TblBtsInstRouteDO tmpInstRoute = new TblBtsInstRouteDO();
		try {
			InputStream inputStream = file.getInputStream();
			ExcelReader read = new ExcelReader();
			List<List<Object>> read2007Excel = read.read2007Excel(inputStream);
			for (int i = 1; i < read2007Excel.size(); i++) {
				List<Object> list = read2007Excel.get(i);
				tmpInstRoute = new TblBtsInstRouteDO();
				tmpInstRoute.setInstCode(list.get(0).toString());
				tmpInstRoute.setInstMerId(list.get(1).toString());
				tmpInstRoute.setInstMerTermId(list.get(2).toString());
				tmpInstRoute.setGateId(list.get(3).toString());
				tmpInstRoute.setBankMerId(list.get(4).toString());
				tmpInstRoute.setBankTermId(list.get(5).toString());
				tmpInstRoute.setStat("N"); //状态默认为不可用
				data.add(tmpInstRoute);
				acceptLen ++;
			}
		} catch (IOException e) {
			logger.error("文件【{}】读取失败：{}", filename, e);
			throw new BposException("文件读取失败");
		}

		for (TblBtsInstRouteDO rout : data) {
			try {
				instRouteService.insert(rout);
				saveLen++;
			} catch (Exception e) {
				logger.error("保存【{}】失败，原因：{}",JsonUtil.toJson(rout),e);
			}
		}
		String message = "";
		if(saveLen == acceptLen){
			message = BjuiAjaxReturnStatusUtil.succeed("成功： "+saveLen + " ,失败："+ (acceptLen - saveLen));
		}else{
			message = BjuiAjaxReturnStatusUtil.error("成功： "+saveLen + " ,失败："+ (acceptLen - saveLen));
		}
		BjuiAjaxReturnStatusUtil.retJson(response, message);
		return null;
	}

	@RequestMapping("add")
	@RequiresAuthentication
	public String doAdd(@ModelAttribute("rout") TblBtsInstRouteDO rout, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws BposException {
		instRouteService.insert(rout);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}

	@RequestMapping("delete/{name:.*}")
	@RequiresAuthentication
	public String deleteUser(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) {
		int parseInt = Integer.parseInt(name);
		TblBtsInstRouteDO gaterout = instRouteService.selectByPrimaryKey(parseInt);
		String message = "";
		if (gaterout != null) {
			InstRouteForm rout = new InstRouteForm();
			rout.setRouteSeq(parseInt);
			instRouteService.deleteByPrimaryKey(parseInt);
			message = BjuiAjaxReturnStatusUtil.succeedDel();
		} else {
			message = BjuiAjaxReturnStatusUtil.error("未找到该记录");
		}
		BjuiAjaxReturnStatusUtil.retJson(response, message);
		return null;
	}

	@RequestMapping("updatepage/{name:.*}")
	@RequiresAuthentication
	public String doUpdateUserPage(@PathVariable String name, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		TblBtsInstRouteDO gaterout = instRouteService.selectByPrimaryKey(Integer.parseInt(name));
		request.setAttribute("rout", gaterout);
		return "instroute/updatePage";
	}

	
	@RequestMapping("update")
	@RequiresAuthentication
	public String doUpdate(@ModelAttribute("rout") TblBtsInstRouteDO rout, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws BposException {
		instRouteService.updateByPrimaryKey(rout);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}
	
	@RequestMapping("updatestat/{id:.*}/{stat:.*}")
	@RequiresAuthentication
	public String doUpdateStat(@PathVariable Integer id,@PathVariable String stat, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws BposException {
		TblBtsInstRouteDO rout = new TblBtsInstRouteDO();
		rout.setStat(stat);
		rout.setRouteSeq(id);
		instRouteService.updateStat(rout);
		String succeed = BjuiAjaxReturnStatusUtil.succeed();
		BjuiAjaxReturnStatusUtil.retJson(response, succeed);
		return null;
	}

}
