package com.dragon.project.system.carouselMap.entity;

import com.dragon.framework.aspectj.lang.annotation.Excel;
import com.dragon.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/03 10:11
 * @description： 轮播图设置(CarouselMap)实体类
 * @modified By：
 * @version: 1.0.0
 */
public class CarouselMap extends BaseEntity {
    private static final long serialVersionUID = -42774646867334310L;

    @Excel(name = "轮播图序号")
    private Integer carouselId;
    /**
     * 背景图片地址
     */
    @Excel(name = "背景图片地址")
    private String imgUrl;
    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;
    /**
     * 副标题
     */
    @Excel(name = "副标题")
    private String subTitle;
    /**
     * 点击的url地址
     */
    @Excel(name = "点击的url地址")
    private String url;
    /**
     * 是否当前窗口打开，0表示否，1表示是
     */
    @Excel(name = "是否当前窗口打开：0表示否，1表示是")
    private String target;
    /**
     * 是否显示
     */
    @Excel(name = "显示状态")
    private String display;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Integer carouselId) {
        this.carouselId = carouselId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("carouselId", carouselId)
                .append("imgUrl", imgUrl)
                .append("title", title)
                .append("subTitle", subTitle)
                .append("url", url)
                .append("target", target)
                .append("display", display)
                .toString();
    }
}