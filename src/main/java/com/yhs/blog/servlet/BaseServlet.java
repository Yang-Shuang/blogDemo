package com.yhs.blog.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhs.blog.entity.BaseEntity;
import com.yhs.blog.entity.ResponseEntity;
import com.yhs.blog.utils.LogUtil;
import com.yhs.blog.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by
 * Yang on 2018/3/18.
 */
public class BaseServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("We are not Support HTTP GET");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String jsonData = "";
        try {
            BufferedReader stream =  new BufferedReader(new InputStreamReader(req.getInputStream()));
            String jsonStr = null;
            StringBuilder builder = new StringBuilder();
            while ((jsonStr = stream.readLine()) != null){
                builder.append(jsonStr);
            }
            stream.close();
            String jsonParames = builder.toString();
            if (StringUtil.isEmpty(jsonParames)) {
                jsonData = sendResponse(null);
            } else {
                jsonData = sendResponse(jsonParames);
            }

        } catch (Exception e){
            LogUtil.print(e.getMessage());
            ObjectMapper mapper = new ObjectMapper();
            jsonData = mapper.writeValueAsString(new ResponseEntity("-111","服务器错误"));
        }
        PrintWriter writer = resp.getWriter();
        writer.print(jsonData);
        writer.flush();
        writer.close();
    }

    protected <T extends BaseEntity> String sendResponse(String json)  throws IOException,JsonProcessingException{
        return "";
    };
}
