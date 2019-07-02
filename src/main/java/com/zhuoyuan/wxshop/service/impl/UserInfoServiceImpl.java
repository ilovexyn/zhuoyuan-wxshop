package com.zhuoyuan.wxshop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhuoyuan.wxshop.model.UserInfo;
import com.zhuoyuan.wxshop.mapper.UserInfoMapper;
import com.zhuoyuan.wxshop.request.Result;
import com.zhuoyuan.wxshop.service.IUserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuoyuan.wxshop.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 9216 kB 服务实现类
 * </p>
 *
 * @author Wangjie
 * @since 2019-06-30
 */
@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public Result getWxuser(String code) {
        String appId ="wx12fee77b24ac4a51";
        String secret ="431be374f821923354e50b53bfeda46b";
        String js_code =code;
        String grant_type ="authorization_code";
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        url=url+"?appid="+appId;
        url=url+"&secret="+secret;
        url=url+"&js_code="+js_code;
        url=url+"&grant_type="+grant_type;
        JSONObject jsonObject =  HttpClientUtils.httpGet(url);
        log.info("jsonObject:"+((JSONObject) jsonObject).toString());
        return  Result.success(jsonObject);
    }

    @Override
    public Result save(UserInfo userInfo) {
        userInfo.setCt(new Date());
        userInfo.setUt(new Date());
        userInfoMapper.insert(userInfo);
        return Result.success();
    }

    public  String decode(String param){
        String result= null;
        try {
            result = new String(param.getBytes("utf-8"), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
