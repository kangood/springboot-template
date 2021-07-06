package net.kyong.template.web.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.kyong.template.common.ResultModel;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("module-name")
public class TemplateController {

    @GetMapping("getDo")
    public Object getDo(@RequestParam String id) {
        ResultModel<Object> resultModel = new ResultModel<>();
        try {
            log.info("==========进入module-name/getDo, id=" + id);

            log.info(resultModel.toString());
        } catch (Exception e) {
            e.printStackTrace();
            resultModel.err500(resultModel, e);
        }
        return resultModel;
    }

    @PostMapping("postDo")
    public Object postDo(@RequestBody JSONObject requestData) {
        ResultModel<Object> resultModel = new ResultModel<>();
        try {
            log.info("==========进入module-name/postDo, requestData=" + requestData);

            log.info(resultModel.toString());
        } catch (Exception e) {
            e.printStackTrace();
            resultModel.err500(resultModel, e);
        }
        return resultModel;
    }

}
