package cn.com.hqep.templet.service.impl;


import cn.com.hqep.templet.common.DateUtil;
import cn.com.hqep.templet.common.ExcelUtil;
import cn.com.hqep.templet.common.jsonMsg;
import cn.com.hqep.templet.dao.templetDao;
import cn.com.hqep.templet.model.tblBbgsTemplateModel;
import cn.com.hqep.templet.service.templetService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 模板实现类
 *
 * @author hq4
 * @date 2017年10月13日 下午03:32:14
 */
@Repository
public class templetServiceImpl implements templetService {
    private Logger log = Logger.getLogger(templetServiceImpl.class);

    @Autowired
    private templetDao dao;

    public Map saveTemplet(tblBbgsTemplateModel templet) {
        //UUID 去除 - 作为 ID 本版本未使用
        templet.setId(UUID.randomUUID().toString().replace("-", ""));
        templet.setUptime(DateUtil.time8().toString());
        if (dao.queryCount(templet) > 0) {
            return jsonMsg.toJosn("error", "存在相同模板名称");
        }
        if (dao.saveTemplet(templet) > 0) {
            return jsonMsg.toJosn("success", "保存成功");
        }
        return jsonMsg.toJosn("error", "保存失败");
    }

    /**
     * 查询所有符合条件的模板
     *
     * @param templet
     * @return
     */
    public List<tblBbgsTemplateModel> listAll(tblBbgsTemplateModel templet) {
        return dao.listAll(templet);
    }

    public tblBbgsTemplateModel queryModelById(String id) {
        return null;
    }

    /**
     * 删除模板
     *
     * @param templet
     * @return
     */
    public boolean delTemplet(tblBbgsTemplateModel templet) {
        return dao.delTemplet(templet) > 0;
    }

    /**
     * 查询记录数
     *
     * @param templet
     * @return
     */
    public int queryCount(tblBbgsTemplateModel templet) {
        return dao.queryCount(templet);
    }

    /**
     * 设置默认模板
     *
     * @param id
     * @return
     */
    public boolean setDefault(String id) {
        try {
            tblBbgsTemplateModel template = dao.queryTempletModelById(id);
            template.setIsDefault("Y");
            tblBbgsTemplateModel model = new tblBbgsTemplateModel();
            model.setTemplateType(template.getTemplateType());
            model.setIsDefault("Y");
            if (dao.listAll(model).size() > 0) {
                tblBbgsTemplateModel model2 = dao.listAll(model).get(0);
                model2.setIsDefault("N");
                dao.updateModel(model2);
            }
            dao.updateModel(template);
            return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    /**
     * 查看模板详情 将模板Excel 生成 htm
     *
     * @param id
     * @return
     */
    public Map viewDetail(String id, String sourcePath){
        tblBbgsTemplateModel model = dao.queryTempletModelById(id);
        File xlsFile = new File(sourcePath+model.getFilePath());
        if(!xlsFile.exists())
            return jsonMsg.toJosn("error","该模板已经不存在，请重新上传");
        File file = new File(sourcePath + "WEB-INF/page/bbgs/excelHtml/" + model.getRealName().replace(".xlsx", "").replace(".xls", "") + ".htm");
        if (file.exists())
            file.delete();
        ExcelUtil.ExcelToHtm(sourcePath+"WEB-INF/page/bbgs/excelHtml/", model.getRealName());
        return jsonMsg.toJosn("success", model);
    }
}
