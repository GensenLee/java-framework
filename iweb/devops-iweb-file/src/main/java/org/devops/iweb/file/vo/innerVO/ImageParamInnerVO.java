package org.devops.iweb.file.vo.innerVO;

import org.devops.core.utils.util.IntUtil;

public class ImageParamInnerVO {

    /**
     * 宽
     */
    private Integer width = 0;

    /**
     * 高
     */
    private Integer height = 0;

    /**
     * 质量
     */
    private Integer quality = 0;

    /**
     * 是否裁剪
     */
    private Boolean wh = false;

    /**
     * 类型
     */
    private String type;

    public Integer getWidth() {
        return width == null ? 0 : width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height == null ? 0 : height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getQuality() {
        return quality == null ? 0 : quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }


    public Boolean isWh() {
        return wh == null ? false : wh;
    }

    public void setWh(Boolean wh) {
        this.wh = wh;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ImageParamInnerVO(String param, String type) {
        for (String tmp : param.split("_")) {
            if (tmp.toLowerCase().contains("wh")) {
                this.wh = IntUtil.toInt(tmp.toLowerCase().replace("wh", "")) == 1;
            } else if (tmp.toLowerCase().contains("w")) {
                this.width = IntUtil.toInt(tmp.toLowerCase().replace("w", ""));
            } else if (tmp.toLowerCase().contains("h")) {
                this.height = IntUtil.toInt(tmp.toLowerCase().replace("h", ""));
            } else if (tmp.toLowerCase().contains("q")) {
                this.quality = IntUtil.toInt(tmp.toLowerCase().replace("q", ""));
            }
        }
        this.type = type;
    }

    @Override
    public String toString() {
        String param = "";
        if (this.getWidth() != 0) {
            param += "_" + this.getWidth() + "w";
        }
        if (this.getHeight() != 0) {
            param += "_" + this.getHeight() + "h";
        }
        if (this.isWh()) {
            param += "_" + "1wh";
        }
        if (this.getQuality() != 0) {
            param += "_" + this.getQuality() + "q";
        }
        return "".equals(param) ? "" : param.substring(1) + "." + this.getType();
    }


}
