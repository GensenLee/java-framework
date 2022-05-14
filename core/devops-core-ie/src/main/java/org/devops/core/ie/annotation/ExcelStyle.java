package org.devops.core.ie.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
@Inherited
@Documented
public @interface ExcelStyle {

	/**
	 * 位置  （*）表示所有
	 * @return
	 */
	String[] position() default "*";
	
	/**
	 * 左对齐
	 * @return
	 */
	HorizontalAlignment alignment() default HorizontalAlignment.CENTER;
	
	/**
	 * 居中对齐
	 * @return
	 */
	VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;
	
	/**
	 * 自动换行
	 * @return
	 */
	boolean wrapText() default true;
	
	/**
	 * 是否有边框
	 * @return
	 */
	boolean border() default true;
	
	
	int fontHeight() default 12;
	
	String fontName() default "微软雅黑";
	
	// black
	short fontColor() default 8;
	
	
}
