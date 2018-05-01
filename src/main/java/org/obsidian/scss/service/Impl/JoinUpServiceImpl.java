package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.JoinUpMapper;
import org.obsidian.scss.entity.AccessAndNumDuring;
import org.obsidian.scss.entity.JoinUp;
import org.obsidian.scss.entity.JoinUpExample;
import org.obsidian.scss.service.JoinUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by mobing  on 2017/7/13.
 */
@Service
public class JoinUpServiceImpl implements JoinUpService {

    @Autowired
    private JoinUpMapper joinUpMapper;

    @Transactional
    public String selectAccountByAccessIdAndClientId(int accessId, int clientId){
        JoinUpExample example = new JoinUpExample();
        example.or().andClientIdEqualTo(clientId).andAccessIdEqualTo(accessId);
        List<JoinUp> list = joinUpMapper.selectByExample(example);
        if(list == null || list.size() == 0){
            return null;
        }
        return list.get(0).getAccount();
    }

    @Transactional
    public int selectByAccessIdAndClientId(int accessId, int clientId) {
        JoinUpExample example = new JoinUpExample();
        example.or().andClientIdEqualTo(clientId).andAccessIdEqualTo(accessId);
        List<JoinUp> list = joinUpMapper.selectByExample(example);
        if(list == null || list.size() == 0){
            return 0;
        }
        return list.size();
    }

    @Transactional
    public int insertJoinUp(int accessId,int clientId,String account){
        JoinUp joinUp = new JoinUp();
        joinUp.setAccessId(accessId);
        joinUp.setClientId(clientId);
        joinUp.setTime(new Date().getTime());
        joinUp.setAccount(account);
        return joinUpMapper.insertSelective(joinUp);
    }

    @Transactional
    public int updateJoinUp(int accessId,int clientId,String account){
        JoinUpExample example = new JoinUpExample();
        example.or().andAccessIdEqualTo(accessId).andClientIdEqualTo(clientId);
        JoinUp joinUp = new JoinUp();
        joinUp.setAccount(account);
        return joinUpMapper.updateByExampleSelective(joinUp,example);
    }

    @Transactional
    public int deleteJoinUp(int accessId, int clientId) {
        JoinUpExample example = new JoinUpExample();
        example.or().andAccessIdEqualTo(accessId).andClientIdEqualTo(clientId);
        return joinUpMapper.deleteByExample(example);
    }

    @Transactional
    public Long getTodayClientCount() {
        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60
                * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND)
                * 1000);
        Long dayStartTime = date2.getTime();
        Long nowTime = date.getTime();

        return joinUpMapper.getTodayClientCount(dayStartTime,nowTime);
    }

    @Transactional
    public int updateAllJoinUp(int clientId,String qq, String wx, String weibo) {
        int qqSum = this.selectByAccessIdAndClientId(2,clientId);
        String nowQQ = this.selectAccountByAccessIdAndClientId(2,clientId);
        if(qqSum > 0 && "".equals(qq)) {
            this.deleteJoinUp(2, clientId);
        }
        else if(qqSum == 0 && !"".equals(qq) && qq.length() > 0){
            this.insertJoinUp(2,clientId,qq);
        }
        else if(qqSum > 0 && nowQQ != null && nowQQ.equals(qq)){
            this.updateJoinUp(2,clientId,qq);
        }
        int wxSum = this.selectByAccessIdAndClientId(3,clientId);
        String nowWx = this.selectAccountByAccessIdAndClientId(3,clientId);
        if(wxSum > 0 && "".equals(wx)){
            this.deleteJoinUp(3,clientId);
        }
        else if(wxSum == 0 && !"".equals(wx) && wx.length() > 0){
            this.insertJoinUp(3,clientId,wx);
        }
        else if(wxSum > 0 && nowWx != null && !nowWx.equals(wx)){
            this.updateJoinUp(3,clientId,wx);
        }
        int weiboSum = this.selectByAccessIdAndClientId(4,clientId);
        String nowWeibo = this.selectAccountByAccessIdAndClientId(4,clientId);
        if(weiboSum > 0 && "".equals(weibo)){
            this.deleteJoinUp(4,clientId);
        }
        else if(weiboSum == 0 && !"".equals(weibo) && weibo.length() > 0){
            this.insertJoinUp(4,clientId,weibo);
        }
        else if(weiboSum > 0 && nowWeibo != null && !nowWeibo.equals(weibo)){
            this.updateJoinUp(4,clientId,weibo);
        }
        return 0;
    }

    /**
     *根据接入方式和接入账号判断该用户之前是否接入过
     */
    public List<JoinUp> hasJoinedUp(int accessId, String account) {
        return joinUpMapper.selectByRecord(accessId, account);
    }

    /**
     * 对于新接入的用户添加接入记录
     */
    public int addJoinUp(int accessId,int clientId, long time, String account) {
        return joinUpMapper.insertSelective(new JoinUp(accessId, clientId, time, account));
    }

    /**
     *根据用户编号查找所有的接入记录
     */
    public List<JoinUp> getByClientId(int clientId) {
        return joinUpMapper.selectByClientId(clientId);
    }

    public JoinUp getLastByClientId(int clientId) {
        return joinUpMapper.selectLastJoinByClientId(clientId);
    }

    public int selectDuringClientNum(long startTime, long stopTime) {
        int res = joinUpMapper.selectDuringClientNum(startTime,stopTime).size();
        return res;
    }

    public List<AccessAndNumDuring> selectAccess(long startTime, long stopTime) {
        return joinUpMapper.selectAccess(startTime,stopTime);
    }
}
