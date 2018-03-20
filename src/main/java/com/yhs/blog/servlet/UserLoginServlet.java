package com.yhs.blog.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhs.blog.bean.UserBean;
import com.yhs.blog.dbhelper.DBUserHelper;
import com.yhs.blog.entity.ResponseEntity;
import com.yhs.blog.entity.UserLoginEntity;
import com.yhs.blog.utils.LogUtil;
import com.yhs.blog.utils.StringUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by YangShuang
 * on 2018/3/20.
 */
public class UserLoginServlet extends BaseServlet {


    protected String sendResponse(String json) throws IOException,JsonProcessingException {
        String jsonData = "";
        ObjectMapper mapper = new ObjectMapper();
        if (StringUtil.isEmpty(json)) {
            jsonData = mapper.writeValueAsString(new ResponseEntity("-111", "服务器错误"));
        } else {
            LogUtil.print(json);
            UserLoginEntity entity = mapper.readValue(json, UserLoginEntity.class);
            if (entity != null && entity.getUserName() != null && entity.getPassWord() != null) {
                try {
                    List<UserBean> bean = DBUserHelper.getUserData( entity.getUserName(),entity.getPassWord());
                    if (bean != null && bean.size() > 0){
                        jsonData = mapper.writeValueAsString(new ResponseEntity("0", "登陆成功"));
                    } else {
                        jsonData = mapper.writeValueAsString(new ResponseEntity("-101", "用户名或密码错误"));
                    }
                } catch (SQLException e) {
                    LogUtil.print(e.getMessage());
                    jsonData = mapper.writeValueAsString(new ResponseEntity("-111", "服务器错误"));
                } catch (InstantiationException e) {
                    LogUtil.print(e.getMessage());
                    jsonData = mapper.writeValueAsString(new ResponseEntity("-111", "服务器错误"));
                } catch (IllegalAccessException e) {
                    LogUtil.print(e.getMessage());
                    jsonData = mapper.writeValueAsString(new ResponseEntity("-111", "服务器错误"));
                }
            } else {
            }
        }
        return jsonData;
    }


}
