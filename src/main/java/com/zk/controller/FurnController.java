package com.zk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zk.bean.Furn;
import com.zk.service.FurnService;
import com.zk.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

@Controller
public class FurnController {

    @Resource
    private FurnService furnService;

    /*解读：请求 http://localhost:8080 转发到首页*/
    @GetMapping(value = {"/"})
    public String goTo(){
        return "index";
    }

    /*说明：
     *   1.前端以json方式发送furn对象
     *   2.接收参数时加上@RequestBody
     *   3.如果出现了错误，底层会把错误信息封装到Errors errors中
     * */
    @PostMapping("/save")
    @ResponseBody
    public Result save(@Validated @RequestBody Furn furn, Errors errors) {
        HashMap<String, Object> errorsMap = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError error : fieldErrors) {
            errorsMap.put(error.getField(), error.getDefaultMessage());
        }
        if (errorsMap.isEmpty()) {
            furnService.save(furn);
            return Result.success();
        }
        return Result.error("1001", "后端表单校验失败，请重新正确填写！", errorsMap);
    }

    @GetMapping("/list")
    @ResponseBody
    public Result list() {
        List<Furn> furnList = furnService.list();
        return Result.success(furnList);
    }

    @GetMapping("/getById")
    @ResponseBody
    public Result getById(@RequestParam("id") String id) {
        Furn furn = furnService.getById(id);
        return Result.success(furn);
    }

    @PutMapping("/update")
    @ResponseBody
    public Result updateById(@RequestBody Furn furn) {
        furnService.updateById(furn);
        return new Result("200", "修改成功", null);
    }

    @DeleteMapping("/delById")
    @ResponseBody
    public Result delById(@RequestParam("id") String id) {
        furnService.removeById(id);
        return Result.success();
    }

    /*解读：分页查询*/
    @GetMapping("/page")
    @ResponseBody
    public Result pageList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        /*解读：page对象中封装了分页相关的所有数据*/
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize));
        return Result.success(page);
    }

    /*解读：带条件检索分页显示*/
    //@GetMapping("/page2")
    //public Result pageList2(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
    //                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
    //                        @RequestParam(value = "search", defaultValue = "") String search) {
    //    /*解读：封装查询条件*/
    //    QueryWrapper<Furn> queryWrapper = Wrappers.query();
    //    /*解读工具类：str != null && !str.isEmpty() && containsText(str)*/
    //    if (StringUtils.hasText(search)) {
    //        queryWrapper.like("name", search);
    //    }
    //
    //    /*解读：page对象中封装了分页相关的所有数据*/
    //    Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize), queryWrapper);
    //    return Result.success(page);
    //}

    /*解读：编写方法，使用lambdaQueryWrapper封装查询条件*/
    /*解读：带条件检索分页显示*/
    @GetMapping("/page2")
    @ResponseBody
    public Result pageList2(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "search", defaultValue = "") String search) {
        /*解读：封装查询条件*/
        LambdaQueryWrapper<Furn> lambdaQueryWrapper = Wrappers.<Furn>lambdaQuery();
        /*解读工具类：str != null && !str.isEmpty() && containsText(str)*/
        if (StringUtils.hasText(search)) {
            lambdaQueryWrapper.like(Furn::getName, search);
        }

        /*解读：page对象中封装了分页相关的所有数据*/
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize), lambdaQueryWrapper);
        return Result.success(page);
    }
}
