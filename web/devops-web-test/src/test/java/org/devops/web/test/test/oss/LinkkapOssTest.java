package org.devops.web.test.test.oss;

import org.devops.core.utils.constant.FileType;
import org.devops.iweb.file.core.FileOssCore;
import org.devops.mjar.autotest.action.AbstractJUnit4ServiceAction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author GENSEN
 * @version 1.00
 * @time 2022/2/14 11:53
 * @description
 */
public class LinkkapOssTest extends AbstractJUnit4ServiceAction {

    @Autowired
    private FileOssCore fileOssCore;

    @Test
    public void upload() throws FileNotFoundException {

        String upload = fileOssCore.upload(new FileInputStream("C:\\Users\\GENSEN\\Documents\\581b0084-12d8-4480-86ad-fe4c1488393e.docx"), FileType.DOCX);
        System.out.println(upload);

    }



}
