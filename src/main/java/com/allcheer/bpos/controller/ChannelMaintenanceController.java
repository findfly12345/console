package com.allcheer.bpos.controller;

import com.allcheer.bpos.domain.ChannelInfoBO;
import com.allcheer.bpos.form.ChannelFileUploadForm;
import com.allcheer.bpos.form.ChannelForm;
import com.allcheer.bpos.service.ChannelService;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.ExcelReader;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.constant.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by fireWorks on 2016/3/10.
 */

@Controller
@RequestMapping(value = "/CM")
public class ChannelMaintenanceController {

    private static final Logger logger = LoggerFactory.getLogger(ChannelMaintenanceController.class);

    @Autowired
    ChannelService channelService;

    @RequestMapping(value = "/maintenance_the_channel")
    public String goChannelMantenancePage(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("channelForm")ChannelForm channelForm){

        ChannelInfoBO channelInfoBO = new ChannelInfoBO();
        if (channelForm.getGateId() != null && !channelForm.getGateId().trim().equals("")){
            channelInfoBO.setGateId(channelForm.getGateId());
        }
        if (channelForm.getPosMerId() != null && !channelForm.getPosMerId().trim().equals("")){
            channelInfoBO.setPosMerId(channelForm.getPosMerId());
        }
        if (channelForm.getPosTermId() != null && !channelForm.getPosTermId().trim().equals("")){
            channelInfoBO.setPosTermId(channelForm.getPosTermId());
        }

        String pageCurrent = channelForm.getPageCurrent();
        String pageSize = channelForm.getPageSize();

        channelInfoBO.setPageCurrent(Integer.valueOf(pageCurrent));
        channelInfoBO.setPageSize(Integer.valueOf(pageSize));

        Pagination<ChannelInfoBO> channelInfoBOPagination = channelService.getTheChannel(channelInfoBO);

        channelForm.setPagination(channelInfoBOPagination);

        return "channel_maintenance_list";

    }

    @RequestMapping(value= "edit_channel_info")
    public String goEditChannelInfoPage(HttpServletRequest request,  @ModelAttribute("channelForm")ChannelForm channelForm){

        String gateId = request.getParameter("id");
        String merId = request.getParameter("mId");
        String termId = request.getParameter("tId");

        if(gateId == null || gateId.trim().equals("")){
            return "add_new_channel_info";
        }

        ChannelInfoBO channelInfoBO = new ChannelInfoBO();
        channelInfoBO.setGateId(gateId);
        channelInfoBO.setPosMerId(merId);
        channelInfoBO.setPosTermId(termId);

        channelInfoBO  = channelService.selectTheChannel(channelInfoBO);
        channelForm.setGateId(channelInfoBO.getGateId());
        channelForm.setPosMerId(channelInfoBO.getPosMerId());
        channelForm.setPosTermId(channelInfoBO.getPosTermId());
        channelForm.setBatchId(channelInfoBO.getBatchId());
        channelForm.setInstId(channelInfoBO.getInstId());
        channelForm.setInstName(channelInfoBO.getInstName());

        return "add_new_channel_info";

    }

    @ResponseBody
    @RequestMapping(value= "save_channel_info")
    public Map saveChannelInfo(HttpServletRequest request, @ModelAttribute("channelForm")ChannelForm channelForm){
        Map resultMap  = new HashMap();

        ChannelInfoBO channelInfoBO = new ChannelInfoBO();
        channelInfoBO.setGateId(channelForm.getGateId());
        channelInfoBO.setPosMerId(channelForm.getPosMerId());
        channelInfoBO.setPosTermId(channelForm.getPosTermId());
        channelInfoBO.setBatchId(channelForm.getBatchId());
        channelInfoBO.setInstId(channelForm.getInstId());
        channelInfoBO.setInstName(channelForm.getInstName());

        resultMap = channelService.saveChannelInfo(channelInfoBO);


        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value= "delete_channel")
    public Map deleteChannelInfo(HttpServletRequest request, @ModelAttribute("channelForm")ChannelForm channelForm){
        Map resultMap  = new HashMap();

        String gateId = request.getParameter("id");
        String merId = request.getParameter("mId");
        String termId = request.getParameter("tId");

        ChannelInfoBO channelInfoBO = new ChannelInfoBO();
        channelInfoBO.setGateId(gateId);
        channelInfoBO.setPosMerId(merId);
        channelInfoBO.setPosTermId(termId);

        resultMap = channelService.deleteChannelInfo(channelInfoBO);


        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = {"/upload_file"})
    public Map uploadFile(HttpServletRequest request) {

        Map resultMap = new HashMap<>();
        String fileUrl = "";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        String fileName = multipartFile.getOriginalFilename();

        File targetFile = new File(CommonConstants.INST_MER_FILE_PATH + DateUtil.currentTimestamp2String(DateUtil.PATTERN_DATE_8), fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        } else {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "已存在同名文件!");
            targetFile.delete();
            targetFile.mkdirs();
        }
        try {
            multipartFile.transferTo(targetFile);
            fileUrl = targetFile.getPath();
        } catch (Exception e) {
            logger.error("上传文件异常{}", e.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "上传文件失败!");
            return resultMap;
        }

        resultMap.put("statusCode", 200);
        resultMap.put("message", "文件上传成功!");
        resultMap.put("filename", fileName);
        resultMap.put("fileurl", fileUrl);


        return resultMap;
    }

    @RequestMapping(value = "/add_channel_info_batch", method = RequestMethod.GET)
    public String goAddMapForInstChannelBatch(@ModelAttribute("channelFileUploadForm")ChannelFileUploadForm channelFileUploadForm){
        return "add_channel_info_batch";
    }
    @ResponseBody
    @RequestMapping(value = "/import_channel_info",method = RequestMethod.POST)
    public Map importChannelInfo(@ModelAttribute("channelFileUploadForm") ChannelFileUploadForm channelFileUploadForm){

        Map resultMap = new HashMap();
        String uploadFile = channelFileUploadForm.getUploadFile();

        if(( uploadFile==null || uploadFile == " ")){
            logger.error("上传文件未知{}",uploadFile);
            resultMap.put("statusCode", 300);
            resultMap.put("message", "上传文件未知");
            return resultMap;
        }

        List<List<Object>> uploadFileList = new ArrayList<>();
        try{
            uploadFileList =parseUploadFile(uploadFile);
        }catch(IOException ex){
            logger.error(ex.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "文件导入失败");
            return resultMap;
        }catch(Exception ex){
            logger.error(ex.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "文件导入出错");
            return resultMap;
        }
        resultMap = channelService.importChannelInfo(uploadFileList);

        return resultMap;
    }

    private static List<List<Object>> parseUploadFile(String filePath) throws IOException{
        ExcelReader excelReader = new ExcelReader();
        List<List<Object>> list = new LinkedList<List<Object>>();
        File file = new File(filePath);
        String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                .substring(fileName.lastIndexOf(".") + 1);

        if ("xls".equals(extension)) {
            list = excelReader.read2003Excel(file);
        } else if ("xlsx".equals(extension)) {
            list = excelReader.read2007Excel(file);
        } else {
            throw new IOException("上传文件类型不支持");
        }

        return list;

    }

}
