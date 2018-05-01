package org.obsidian.scss.controller;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.IdList;
import org.obsidian.scss.bean.Show;
import org.obsidian.scss.entity.CommonLanguage;
import org.obsidian.scss.service.CommonLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mobing  on 2017/7/17.
 */
@Controller
public class CommonLanguageController {
    @Autowired
    CommonLanguageService commonLanguageService;
    
    @RequestMapping("commonLanguage")
    @ResponseBody
    public Show commonLanguage (){
        List<CommonLanguage> list = commonLanguageService.selectAllCommonLanguage();
        Show show = new Show();
        show.setData(list);
        return show;
    }
    
    
    @RequestMapping("deleteCommonLanguage")
    @ResponseBody
    public Show deleteLanguage(@RequestParam("deleteId") String idList){
        Gson gson = new Gson();
        List<IdList> commonId = gson.fromJson(idList,new TypeToken<List<IdList>>(){}.getType());
        Show show = new Show();
        int fail  = 0 ;
        for (int i = 0 ; i < commonId.size() ; i++){
            int res = commonLanguageService.deleteCommonLanguage(commonId.get(i).getId());
            System.out.println(commonId.get(i).getId());
            if (res ==0){
                fail++;
            }
        }
        if (fail !=0){
            show.setStatus(0);
            show.setMessage("删除失败:"+(commonId.size()-fail)+"条");
        }
        return show;
    }
    
    
    @RequestMapping("addCommonLanguage")
    @ResponseBody
    public Show addCommonLanguage(@RequestParam("content")String content){
        Show show = new Show();
        int res = commonLanguageService.insertCommonLanguage(content);
        if (res == 0){
            show.setStatus(0);
            show.setMessage("插入失败");
        }
        return show;
    }
    
    @RequestMapping("updateCommonLanguage")
    @ResponseBody
    public Show updateCommonLanguage(@RequestParam("commonLanguage") String commonLang){
        Show show =  new Show();
        Gson gson = new Gson();
        CommonLanguage common = gson.fromJson(commonLang,new TypeToken<CommonLanguage>(){}.getType());
        int res = commonLanguageService.updateCommonLanguage(common.getCommonLanguageId(),common.getContent());
        if (res == 0) {
            show.setStatus(0);
            show.setMessage("更新失败!");
        }        
        return show;
    }
    
}
