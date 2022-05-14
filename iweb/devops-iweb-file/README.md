# sinmn-iweb-file V2.0.0-SNAPSHOT
## 文件上传、图片预览、OSS签名等相关操作

## 开始使用

先引入parent
```xml
<parent>
  <groupId>com.sinmn</groupId>
  <artifactId>sinmn-parent</artifactId>
  <version>2.0.0-SNAPSHOT</version>
</parent>
```
再引入相应的模块
```xml
<dependency>
  <groupId>com.sinmn.iweb</groupId>
  <artifactId>sinmn-iweb-file</artifactId>
</dependency>
```
打开注解
```java
@EnableIWebFile(path = "${uploadPath}",domain= "${domain}")
public class Bootstrap
```

## 相关文档API
### 本地文件上传
接口地址：/sinmn/file/fileUpload/upload.do

提交方式：POST multipart/form-data

提交参数

|参数名|类型|
|:----|:--|
|file|file|

响应结果
```json
{
  "code": 200,
  "message": "成功",
  "object": {
    "path": "/sinmn/file/image/2019/10/21/e39d51a9-44fe-404a-adce-292e2a36e32d.jpg",  //文件路径
    "fullPath": "http://localhost:53000/sinmn/file/image/2019/10/21/e39d51a9-44fe-404a-adce-292e2a36e32d.jpg",  //文件路径,包含配置的域名
    "oriName": "营业执照.jpg", //文件原名
    "size": 1850003, //文件大小
    "domain": "http://localhost:53000" //域名
  },
  "success": true
}
```
### 图片，文件预览(下载)

文件预览：文件名(例如) http://localhost:53000/sinmn/file/image/2019/10/21/e39d51a9-44fe-404a-adce-292e2a36e32d.jpg

提交方式：GET

提交参数
无


图片预览：文件名(例如) http://localhost:53000/sinmn/file/image/2019/10/21/e39d51a9-44fe-404a-adce-292e2a36e32d.jpg

提交方式：GET

提交参数
@100w_100h_1wh_90q

例如 http://localhost:53000/sinmn/file/image/2019/10/21/e39d51a9-44fe-404a-adce-292e2a36e32d.jpg@100w_100h_1wh_90q

|参数名|描述|
|:----|:--|
|w|图片宽|
|h|图片高|
|1wh|保持比例|
|q|图片质量|
### 阿里云OSS签名

接口地址：/sinmn/file/oss/getSingature.do

提交方式：POST application/json

提交参数

|参数名|类型|是否必填|
|:----|:--|:--|
|companyId|number|N|

响应结果
```json
{
  "code": 200,
  "message": "成功",
  "object": {
    "accessid": "xxxxxxxxxxxxxxxxxxx",  //对应OSSAccessKeyId
    "host": "xxxxxxxxxxxxxxxxxxxxxxxx", //上传域名
    "policy": "xxxxxxxxxxxxxxxxxxxxxxxxxxx", //对应policy
    "signature": "xxxxxxxxxxxxxxxxxxxxx", //对应signature
    "expire": "2019-10-23T13:40:22Z",
    "end_time": 1571808160830,
    "dirs": "upload"
  },
  "success": true
}
```

###OSS文件上传
```
@Component
public class OssUpload {

    @Autowired
    private FileOssCore fileOssCore;
    
    public String upload(MultipartFile multipartFile) {
        try {
            return fileOssCore.upload(multipartFile.getInputStream(), FileType.get(multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            log.error("file upload error", e);
        }
        return null;
    }
}
```


###COS文件上传
```
@Component
public class CosUpload {

    @Autowired
    private FileCosCore fileCosCore;
    
    public String upload(MultipartFile multipartFile) {
        try {
            return fileCosCore.upload(multipartFile.getInputStream(), FileType.get(multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            log.error("file upload error", e);
        }
        return null;
    }
}
```