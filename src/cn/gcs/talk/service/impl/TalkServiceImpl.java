package cn.gcs.talk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.core.service.impl.BaseServiceImpl;
import cn.gcs.talk.dao.TalkDao;
import cn.gcs.talk.entity.Talk;
import cn.gcs.talk.service.TalkService;

@Service("talkService")
@Transactional
public class TalkServiceImpl extends BaseServiceImpl<Talk> implements TalkService {
	
	private TalkDao talkDao;
	@Resource
	public void setTalkDao(TalkDao talkDao) {
		super.setBaseDao(talkDao);
		this.talkDao = talkDao;
	}
	
	
	
}
