package com.dragon.project.blog.category.controller;

import com.dragon.common.utils.poi.ExcelUtil;
import com.dragon.framework.aspectj.lang.annotation.Log;
import com.dragon.framework.aspectj.lang.enums.BusinessType;
import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.framework.web.page.TableDataInfo;
import com.dragon.project.blog.category.domain.Category;
import com.dragon.project.blog.category.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/12 22:12
 * @description： 分类控制层
 * @modified By：
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/blog/category")
public class CategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;


    @RequiresPermissions("blog:category:view")
    @GetMapping()
    public String category() {
        return "blog/category/category";
    }

    @GetMapping("/list")
    @RequiresPermissions("blog:category:list")
    @ResponseBody
    public TableDataInfo list(Category category) {
        startPage();
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add() {
        return "blog/category/add";
    }

    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("blog:category:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Category category) {
        return toAjax(categoryService.insertCategory(category));
    }

    @GetMapping("/edit/{categoryId}")
    public String edit(@PathVariable Integer categoryId, Model model) {
        model.addAttribute("category", categoryService.selectCategoryById(categoryId));
        return "blog/category/edit";
    }

    @PutMapping("/edit")
    @RequiresPermissions("blog:category:edit")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult editSave(Category category) {
        return toAjax(categoryService.updateCategory(category));
    }

    @DeleteMapping("/remove")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("blog:category:remove")
    @ResponseBody
    public AjaxResult remove(Integer[] ids) {
        return toAjax(categoryService.deleteCategoryByIds(ids));
    }

    @PutMapping("/support/{support}")
    @RequiresPermissions("blog:category:support")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult supportSave(Integer categoryId, @PathVariable String support) {
        return toAjax(categoryService.updateCategorySupportById(categoryId, support));
    }

    @PostMapping("/checkCategoryTitleUnique")
    @ResponseBody
    public String checkCategoryTitleUnique(String title) {
        return categoryService.checkCategoryTitleUnique(title);
    }

    @Log(title = "分类管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("blog:category:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Category category) {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        return util.exportExcel(list, "标签数据");
    }
}
