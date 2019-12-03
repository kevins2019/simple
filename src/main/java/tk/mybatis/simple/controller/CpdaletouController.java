package tk.mybatis.simple.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.simple.BaseController;
import tk.mybatis.simple.model.CpdaleTou;
import tk.mybatis.simple.service.CpdaletouService;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class CpdaletouController extends BaseController {

@Autowired
@Qualifier("daletou")
private CpdaletouService cpdaletouService;

    @ApiOperation(value = "daletouQuery",notes = "")
    @RequestMapping(value = "/daletou",method = RequestMethod.GET)
    public List<CpdaleTou> getAllData(){

        return  cpdaletouService.getAll(CpdaleTou.class);
    }


}
