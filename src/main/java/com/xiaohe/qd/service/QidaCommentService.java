package com.xiaohe.qd.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.QidaAnswerCommentDao;
import com.xiaohe.qd.dao.QidaAnswerDao;
import com.xiaohe.qd.dao.QidaMessageDao;
import com.xiaohe.qd.dao.QidaQuestionCommentDao;
import com.xiaohe.qd.dao.QidaQuestionDao;
import com.xiaohe.qd.dao.QidaSwitchDao;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaAnswer;
import com.xiaohe.qd.model.QidaAnswerComment;
import com.xiaohe.qd.model.QidaMessage;
import com.xiaohe.qd.model.QidaQuestion;
import com.xiaohe.qd.model.QidaQuestionComment;

@Service
public class QidaCommentService extends BaseService{
	
	@Resource
	private QidaQuestionCommentDao questionCommentDao;
	@Resource
	private QidaAnswerCommentDao answerCommentDao;
	@Resource
	private QidaAnswerDao qidaAnswerDao;
	@Resource
	private QidaQuestionDao qidaQuestionDao;
	@Resource
	private QidaSwitchDao qidaSwitchDao;
	@Resource
	private QidaMessageDao qidaMessageDao;
	
	public QidaQuestionComment getQc(Long id) {
		return questionCommentDao.get(id);
	}

	public void saveQc(QidaQuestionComment entity) {
		questionCommentDao.save(entity);
	}

	public void deleteQc(Long id) {
		questionCommentDao.delete(id);
	}
	
	public QidaAnswerComment getAc(Long id) {
		return answerCommentDao.get(id);
	}

	public void saveAc(QidaAnswerComment entity) {
		answerCommentDao.save(entity);
	}

	public void deleteAc(Long id) {
		answerCommentDao.delete(id);
	}
	
	/**
	 * 添加评论
	 * @param quetsionId  flag == 0  questionId 代表问题id  == 1 代表答案id
	 * @param content  评论内容
	 * @param flag
	 * @return
	 */
	public int saveComment(FuUser fuUser, Long questionId, String content, Integer flag, HttpServletRequest request) {
		try {
			
			if (1 == flag) {
				QidaAnswer qidaAnswer = qidaAnswerDao.get(questionId);
				if (null == qidaAnswer) {
					return 2;
				}
				QidaAnswerComment qac = new QidaAnswerComment();
				qac.setFuUser(fuUser);
				qac.setQidaAnswer(qidaAnswer);
				qac.setContent(content);
				qac.setState(qidaSwitchDao.get(1L).getAnswerCommentSwitch());
				qac.setIsDelete(0);
				qac.setCreateTime(new Date());
				answerCommentDao.save(qac);
				if(qidaSwitchDao.get(1L).getAnswerCommentSwitch()==1){
					String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
					QidaMessage message = new QidaMessage();
					message.setMessage((null == qac.getFuUser().getNickName()) ? "佚名" : qac.getFuUser().getNickName() + "评论了您关于<a href=" + url + "/web/qida/questionPay/"
							+ qac.getQidaAnswer().getQidaQuestion().getId() + ".html>" + qac.getQidaAnswer().getQidaQuestion().getTitle() + "</a>问题的答案");
					message.setSendFuUser(qac.getFuUser());
					message.setReceiveFuUser(qac.getQidaAnswer().getFuUser());
					message.setHttpCookie(UUID.randomUUID().toString());
					message.setIsRead(0);
					message.setIsDelete(0);
					message.setCreateTime(new Date());
					qidaMessageDao.save(message);
					message.setMessageId(message.getId());
					qidaMessageDao.save(message);
				}
			} else if (0 == flag) {
				QidaQuestion qidaQuestion = qidaQuestionDao.get(questionId);
				if (null == qidaQuestion) {
					return 2;
				}
				QidaQuestionComment qqc = new QidaQuestionComment();
				qqc.setFuUser(fuUser);
				qqc.setQidaQuestion(qidaQuestion);
				qqc.setContent(content);
				qqc.setState(qidaSwitchDao.get(1L).getQuestionCommentSwitch());
				qqc.setIsDelete(0);
				qqc.setCreateTime(new Date());
				questionCommentDao.save(qqc);
				
				if(qidaSwitchDao.get(1L).getQuestionCommentSwitch()==1){
					String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
					QidaMessage message = new QidaMessage();
					message.setMessage((null == qqc.getFuUser().getNickName()) ? "佚名" : qqc.getFuUser().getNickName() + "评论了您关于<a href=" + url + "/web/qida/questionPay/"
							+ qqc.getQidaQuestion().getId() + ".html>" + qqc.getQidaQuestion().getTitle() + "</a>问题");
					message.setSendFuUser(qqc.getFuUser());
					message.setReceiveFuUser(qqc.getQidaQuestion().getFuUser());
					message.setHttpCookie(UUID.randomUUID().toString());
					message.setIsRead(0);
					message.setIsDelete(0);
					message.setCreateTime(new Date());
					qidaMessageDao.save(message);
					message.setMessageId(message.getId());
					qidaMessageDao.save(message);
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public Integer getCountQuestionComment(Map<String, Object> map) {
		return questionCommentDao.getCountQuestionComment(map);
	}
	
	public List<QidaQuestionComment> findListQuestionComment(int i, int pageSize, Map<String, Object> map){
		return questionCommentDao.findListQuestionComment(i, pageSize, map);
	}
	
	public Integer getCountAnswerComment(Map<String, Object> map) {
		return answerCommentDao.getCountAnswerComment(map);
	}
	
	public List<QidaAnswerComment> findListAnswerComment(int i, int pageSize, Map<String, Object> map){
		return answerCommentDao.findListAnswerComment(i, pageSize, map);
	}

}
