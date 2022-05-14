package org.devops.mjar.live.polyv.enums;

import lombok.Getter;

/***
 * 查询重制课件任务列表 任务状态
 */
@Getter
public enum PptRecordStatus {

    Waiting( "waiting", "等待处理" ),
    Process( "process" ,"处理中"),
    Success( "success","重制成功" ),
    Fail( "fail","重制失败" ),
    Uploaded( "uploaded","上传点播成功" ),
    UploadFailed( "uploadedFailed","上传点播失败" );

    PptRecordStatus( String code,String name ){
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

}
