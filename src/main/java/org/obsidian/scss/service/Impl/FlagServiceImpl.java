package org.obsidian.scss.service.Impl;

import org.obsidian.scss.dao.FlagMapper;
import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.entity.FlagExample;
import org.obsidian.scss.service.FlagService;
import org.obsidian.scss.util.KeywordFinder;
import org.obsidian.scss.util.Trie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mobing  on 2017/7/11.
 */
@Service
public class FlagServiceImpl implements FlagService {

    @Autowired
    private FlagMapper flagMapper;

    /**
     * 新增标签
     * @param name
     * @return
     */
    @Transactional
    public int insertFlag(String name) {
        Flag flag = new Flag();
        flag.setName(name);
        return flagMapper.insertSelective(flag);
    }

    /**
     * 删除标签
     * @param flagId
     * @return
     */
    @Transactional
    public int deleteFlag(int flagId) {
        return flagMapper.deleteFlag(flagId);
    }

    /**
     * 更新标签
     * @param flagId
     * @param name
     * @return
     */
    @Transactional
    public int updateFlag(int flagId, String name) {
        Flag flag = new Flag();
        flag.setFlagId(flagId);
        flag.setName(name);
        return flagMapper.updateFlag(flag);
    }

    /**
     * 查询标签
     * @param flagId
     * @return
     */
    @Transactional
    public String selectName(int flagId){
        String name = flagMapper.selectName(flagId);
        if(name.length() <= 0 || name.equals("")){
            return null;
        }
        return name;
    }

    /**
     * 按标签名查询标签ID
     * @param name
     * @return
     */
    @Transactional
    public int selectFlagId(String name) {
       FlagExample example = new FlagExample();
       FlagExample.Criteria criteria =example.createCriteria();
       criteria.andNameEqualTo(name);
       List<Flag> list = flagMapper.selectByExample(example);
       if (list.size()!=0){
           return list.get(0).getFlagId();
       }
        return 0;
    }

    /**
     * 按客户ID查询所有标签
     * @param clientId
     * @return
     */
    @Transactional
    public List<Flag> selectAllFlag(int clientId) {
        List<Flag> list = flagMapper.selectAllFlag(clientId);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list;
    }

    @Transactional
    public List<Flag> selectAllUnusedFlag(int clientId){
        List<Flag> list = flagMapper.selectAllUnusedFlag(clientId);
        if(list.size() == 0 || list == null){
            return null;
        }
        return list;
    }
    
    @Transactional
    public Flag selectAdv(int flagId) {
        FlagExample example = new FlagExample();
        FlagExample.Criteria criteria = example.createCriteria();
        criteria.andFlagIdEqualTo(flagId);
        return flagMapper.selectByExample(example).get(0);
    }
    @Transactional
    public List<Flag> selectFlags(){
        FlagExample example = new FlagExample();
        return flagMapper.selectByExample(example);
    }

    @Transactional
    public List<Flag> getFlagByContent(String content){
        Trie trie = new Trie();
        List<Flag> list = this.selectFlags();
        for(int i=0;i<list.size();i++){
            trie.insert(list.get(i).getName(),list.get(i).getFlagId());
        }
        List<Flag> found = KeywordFinder.findFlagInContent(content,trie);
        return found;
    }

    @Transactional
    public List<String> recommendFlags(Integer clientId, String content) {
        List<Flag> found = this.getFlagByContent(content);
        List<Flag> hasList = new ArrayList<Flag>();
        Set<String> hasSet = new HashSet<String>();
        List<String> recommendFlags = new ArrayList<String>();
        if(clientId != null){
            hasList = this.selectAllFlag(clientId.intValue());
        }
        if(hasList != null && hasList.size() > 0){
            for(int i=0;i<hasList.size();i++){
                hasSet.add(hasList.get(i).getName());
            }
        }
        if(found != null && found.size() > 0){
            for(int i=0;i<found.size();i++){
                if(!hasSet.contains(found.get(i).getName())){
                    recommendFlags.add(found.get(i).getName());
                }
            }
        }
        return recommendFlags;
    }
}
