package com.cloud.base.service;

import com.cloud.base.HiberDao.PersonDao;
import com.cloud.base.model.Person;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.http.HttpClientUtil;
import utils.http.common.HttpConfig;
import utils.http.common.HttpHeader;
import utils.http.common.HttpMethods;
import utils.http.common.HttpResult;
import utils.http.exception.HttpProcessException;

@Service
public class TestService {

    @Autowired
    private PersonDao personDao;

    public void testHttpApi() throws HttpProcessException {
        String url = "http://172.16.13.209:8801/base-portal/backoffice/menu/menu/queryUpdated?keyword=&pageNo=1&pageSize=20&sort=desc";
        Header[] headers = HttpHeader.custom().cookie("SHAREJSESSIONID=b4ce300d-230e-4156-a162-bb3549410a0f").build();
        HttpConfig httpConfig = HttpConfig.custom()
                .method(HttpMethods.GET)
                .headers(headers)
                .url(url);
        String result = HttpClientUtil.get(httpConfig);
        System.out.println(result);

        HttpResult respResult = HttpClientUtil.sendAndGetResp(httpConfig);
        System.out.println("返回结果：\n"+respResult.getResult());
        System.out.println("返回resp-header："+respResult.getRespHeaders());//可以遍历
        System.out.println("返回具体resp-header："+respResult.getHeaders("Date"));
        System.out.println("返回StatusLine对象："+respResult.getStatusLine());
        System.out.println("返回StatusCode："+respResult.getStatusCode());
        System.out.println("返回HttpResponse对象）（可自行处理）："+respResult.getResp());
    }

    // 测试hibernate->mysql
    public Person findPersonByID(Integer id) {
        return personDao.findPersonByID(id);
    }
}
