package com.dragon.project.blog.tag.controller;

import com.dragon.common.utils.poi.ExcelUtil;
import com.dragon.framework.aspectj.lang.annotation.Log;
import com.dragon.framework.aspectj.lang.enums.BusinessType;
import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.framework.web.page.TableDataInfo;
import com.dragon.project.blog.tag.domain.Tag;
import com.dragon.project.blog.tag.service.TagService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/10 06:55
 * @description： 标签控制器
 * @modified By：
 * @version: 1.0.0
 */
@RequestMapping("/blog/tag")
@Controller
public class TagController extends BaseController {

    @Autowired
    TagService tagService;

    @RequiresPermissions("blog:tag:view")
    @GetMapping()
    public String tag() {
        return "blog/tag/tag";
    }

    @RequiresPermissions("blog:tag:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Tag tag) {
        startPage();
        List<Tag> list = tagService.selectTagList(tag);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add() {
        return "blog/tag/add";
    }

    @RequiresPermissions("blog:tag:add")
    @Log(title = "标签管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Tag tag) {
        return toAjax(tagService.insertTag(tag));
    }

    @GetMapping("/edit/{tagId}")
    public String edit(@PathVariable Integer tagId, Model model) {
        model.addAttribute("tag", tagService.selectTagById(tagId));
        return "blog/tag/edit";
    }


    @RequiresPermissions("blog:tag:edit")
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Tag tag) {
        return toAjax(tagService.updateTag(tag));
    }

    @RequiresPermissions("blog:tag:remove")
    @Log(title = "标签管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer[] ids) {
        return toAjax(tagService.deleteTagByIds(ids));
    }


    @PostMapping("/checkTagTitleUnique")
    @ResponseBody
    public String checkCategoryTitleUnique(String title) {
        return tagService.checkTagTitleUnique(title);
    }

    @Log(title = "标签管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("blog:tag:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Tag tag) {
        List<Tag> list = tagService.selectTagList(tag);
        ExcelUtil<Tag> util = new ExcelUtil<Tag>(Tag.class);
        return util.exportExcel(list, "标签数据");
    }
}
