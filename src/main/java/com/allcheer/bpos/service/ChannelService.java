package com.allcheer.bpos.service;

import com.allcheer.bpos.domain.ChannelInfoBO;
import com.allcheer.bpos.util.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by fireWorks on 2016/3/10.
 */
public interface ChannelService {

    public Pagination<ChannelInfoBO>  getTheChannel(ChannelInfoBO channelInfoBO);

    public ChannelInfoBO selectTheChannel(ChannelInfoBO channelInfoBO);

    public Map saveChannelInfo(ChannelInfoBO channelInfoBO);

    public Map deleteChannelInfo(ChannelInfoBO channelInfoBO);

    public Map importChannelInfo(List<List<Object>> uploadFileList);
}
