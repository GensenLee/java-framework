# devops-core-util

## 模块引入
先引入parent
  ```xml
<parent>
    <groupId>org.devops</groupId>
    <artifactId>devops-parent</artifactId>
    <version>6.0.0-SNAPSHOT</version>
</parent>
  ```
再引入相应的模块
  ```xml
  <dependency>
    <groupId>com.devops.core</groupId>
    <artifactId>devops-core-util</artifactId>
  </dependency>
  ```

## org.devops.core.utils.util.AES

| 方法名     | 入参类型                                      | 描述    |
| ---------- | --------------------------------------------- | ------- |
| decrypt    | byte[] content, byte[] keyByte, byte[] ivByte | AES解密 |
| initialize |                                               | 初始化  |
| generateIV | byte[] iv                                     | 生成iv  |



## org.devops.core.utils.util.Base64Util

| 方法名          | 入参类型                      | 描述                   |
| --------------- | ----------------------------- | ---------------------- |
| encode          | byte[] data                   | base64加密             |
| decode          | String str                    | base64解密             |
| fileToByte      | String filePath               | 文件路径转换为字节数组 |
| byteArrayToFile | byte[] bytes, String filePath | 字节数组转换为文件     |
| input2byte      | InputStream inStream          | 输入流转换为字节数组   |



## org.devops.core.utils.util.Base64Util

| 方法名        | 入参类型                            | 描述                 |
| ------------- | ----------------------------------- | -------------------- |
| deepClone     | Object object                       | 深度克隆             |
| copyNODeep    | Object source,Class<T> clazz        | 浅克隆               |
| copyNODeep    | Object source,Object target         | 浅克隆               |
| copy          | Object source,Class<T> clazz        | 对象拷贝             |
| copy          | Object source,Object target         | 对象拷贝             |
| copy          | List source,Class<T> clazz          | 列表拷贝             |
| copyFromExits | Object source, Object target        |                      |
| getAllField   | Class<?> clazz                      | 获取类所有字段       |
| getField      | Class<?> clazz,String name          | 根据名称获取字段     |
| initCreate    | Object obj,long user_id             | 保存创建者id         |
| initCreate    | Object obj,String name              | 保名称存创建者       |
| initModify    | Object obj,long user_id             | 保存修改者id         |
| initModify    | Object obj,String name              | 保存修改者名称       |
| isNotBaseType | Object o                            | 判断是否不为基本类型 |
| isType        | Object o,String name,Class<?> clazz | 判断是否为指定类型   |
| getValue      | Object source,String field          | 获取字段值           |
| findMethod    | Class<?> clazz,String name          | 获取方法             |



## org.devops.core.utils.util.BooleanUtil

| 方法名    | 入参类型  | 描述                 |
| --------- | --------- | -------------------- |
| isTrue    | String b  | 判断字符串是否为true |
| toBoolean | Boolean b | 转换为boolean类型    |



## org.devops.core.utils.util.ByteUtil

| 方法名    | 入参类型     | 描述              |
| --------- | ------------ | ----------------- |
| isZero    | Byte value   | 判断字节是否为0   |
| isNotZero | Byte value   | 判断字节是否不为0 |
| toByte    | Byte value   | 转换为byte类型    |
| toByte    | Object value | 转换为byte类型    |



## org.devops.core.utils.util.CookieUtil

| 方法名       | 入参类型                                                     | 描述                 |
| ------------ | ------------------------------------------------------------ | -------------------- |
| getCookie    | String name                                                  | 获取cookie           |
| deleteCookie | String name                                                  | 删除cookie           |
| setCookie    | String name, String value                                    | 增加cookie           |
| setCookie    | String name, String value, int cookieMaxAge                  | 增加cookie           |
| setCookie    | String name, String value, int cookieMaxAge, String path, String domain | 增加cookie           |
| getString    | String name                                                  | 返回字符串型cookie值 |
| getInt       | String name                                                  | 返回整数型cookie值   |
| getDouble    | String name                                                  | 返回浮点数cookie值   |



## org.devops.core.utils.util.CsvUtil

| 方法名 | 入参类型                                                     | 描述 |
| ------ | ------------------------------------------------------------ | ---- |
| read   | String content, Class<T> clazz                               |      |
| read   | String content, Class<T> clazz, char separator, char quotechar |      |
| write  | List<?> list                                                 |      |
| write  | List<?> list, char separator, char quotechar                 |      |



## org.devops.core.utils.util.DateUtil

| 方法名              | 入参类型                     | 描述                          |
| ------------------- | ---------------------------- | ----------------------------- |
| compareDays         | Date date1，Date date2       | 比较天数                      |
| formatDate          | String date，String format   | 日期格式化（返回Date类型）    |
| formatDateTime      | String date                  | 时间格式化yyyy-MM-dd HH:mm:ss |
| getDateTimeFormat   | Date date                    | 时间格式化yyyy-MM-dd HH:mm:ss |
| getDateFormat       | Date date                    | 日期格式化yyyy-MM-dd          |
| getTimeFormat       | Date date                    | 时间格式化HH:mm:ss            |
| getDateFormat       | Date date, String formatStr  | 日期格式化（返回String类型）  |
| formatDate          | String date                  | 日期格式化yyyy-MM-dd          |
| getFirstDayOfWeek   |                              | 获取当前日期星期一日期        |
| getLastDayOfWeek    |                              | 获取当前日期星期日日期        |
| getFirstDayOfWeek   | Date date                    | 获取日期星期一日期            |
| getLastDayOfWeek    | Date date                    | 获取日期星期日日期            |
| getFirstDayOfMonth  |                              | 获取当前月的第一天            |
| getLastDayOfMonth   |                              | 获取当前月的最后一天          |
| getFirstDayOfMonth  | Date date                    | 获取指定月的第一天            |
| getLastDayOfMonth   | Date date                    | 获取指定月的最后一天          |
| getCurrYearFirstDay |                              | 获取当年第一天 00:00:00       |
| getYearFirstDay     | Date date                    | 获取指定年第一天 00:00:00     |
| getDayBefore        | Date date                    | 获取日期前一天                |
| getDayAfter         | Date date                    | 获取日期后一天                |
| getNowYear          |                              | 获取当前年                    |
| getYear             | Date date                    | 获取年                        |
| getNowMonth         |                              | 获取当前年份                  |
| getMonth            | Date date                    | 获取月份                      |
| getMinute           | Date date                    | 获取分钟                      |
| getNowMonDay        |                              | 获取当月的第几天              |
| getNowMonthDay      |                              | 获取当月天数                  |
| getEveryDay         | Date startDate, Date endDate | 获取时间段的每一天            |
| getFirstMonth       | int monty                    | 获取提前某个月的日期          |
| addSeconds          | Date date, int seconds       | 增加时间（秒）                |
| addMinutes          | Date date, int minutes       | 增加时间（分）                |
| addHours            | Date date, int hours         | 增加时间（时）                |
| addDay              | Date date, int day           | 增加时间（日）                |
| addMonth            | Date date, int month         | 增加时间（月）                |
| compare             | Date date1, Date date2       |                               |
| compareHours        | Date date1, Date date2       |                               |
| getDayBeforeBegin   | Date date, int before        | 获取日期前一天的开始时间      |
| getDayBeforeEnd     | Date date, int before        | 获取日期前一天的结束时间      |
| getDayStart         | Date date                    | 获取日期当天的开始时间        |
| getDayEnd           | Date date                    | 获取日期当天的结束时间        |



## org.devops.core.utils.util.DoubleUtil

| 方法名            | 入参类型      | 描述               |
| ----------------- | ------------- | ------------------ |
| Double2String     | Object object | 转换为String类型   |
| toDouble          | Double d      | 装换为double类型   |
| toDouble          | Object o      | Object转double     |
| BigDecimal2Double | BigDecimal bd | BigDecimal转double |
| isZero            | Double d      | 判断是否为0        |
| isNoZero          | Double d      | 判断是否不为0      |



## org.devops.core.utils.util.EmojiUtils

| 方法名        | 入参类型      | 描述                                |
| ------------- | ------------- | ----------------------------------- |
| containsEmoji | String source | 检测是否有emoji字符                 |
| filterEmoji   | String source | 过滤emoji 或者 其他非文字类型的字符 |



## org.devops.core.utils.util.EncryptUtil

| 方法名        | 入参类型                      | 描述                         |
| ------------- | ----------------------------- | ---------------------------- |
| getMD5        | String str                    | MD5加密                      |
| getLittleMD5  | String str                    | MD5加密（20位）              |
| getSHA1       | String str                    | SHA-1加密                    |
| getLittleSHA1 | String str                    | SHA-1加密（20位）            |
| byte2hex      | byte[] b                      | 将字节数组转换成16进制字符串 |
| hex2byte      | byte[] b                      |                              |
| xorEncrypt    | String str, String key        | 异或加密                     |
| xorDecrypt    | String encryptStr, String key | 异或解密                     |



## org.devops.core.utils.util.ExecutorUtil

| 方法名       | 入参类型                           | 描述     |
| ------------ | ---------------------------------- | -------- |
| executeLimit | FutureTask<T> future ,long timeout | 限时执行 |



## org.devops.core.utils.util.FastJsonUtils

| 方法名                    | 入参类型                                      | 描述                                         |
| ------------------------- | --------------------------------------------- | -------------------------------------------- |
| getStringList             | String jsonData                               | 把JSON数据转换成普通字符串列表               |
| getBean                   | String jsonData, Class<T> clazz               | 把JSON数据转换成指定的java对象               |
| getBean                   | String jsonData, TypeReference<T> type        | 把JSON数据转换成指定的java对象               |
| getBean                   | String jsonData, String field, Class<T> clazz | 把JSON数据里面的某个字段转换成指定的java对象 |
| getMap                    | Object o                                      | 将object转换成map                            |
| getMap                    | String str                                    | 将string转换成map                            |
| getString                 | String jsonData, String field                 | 获取JSON数据里面的某个字段                   |
| getBeanList               | String jsonData, Class<T> clazz               | 把JSON数据转换成指定的java对象列表           |
| getBeanMapList            | String jsonData                               | 把JSON数据转换成较为复杂的java对象列表       |
| toJsonString              | Object obj                                    | 把javaBean 转换成字符串                      |
| toJsonString              | Object obj, SerializerFeature... features     | 把javaBean 转换成字符串                      |
| toJsonStringSingleQuotes  | Object obj                                    | 把javaBean 转换成字符串（输出key使用单引号） |
| toJsonStringDisableCircle | Object obj                                    | 把javaBean 转换成字符串（消除循环引用）      |
| jsonStringToBeanList      | String str, Class<T> clazz                    | 将java 字符串转化为指定类型的java List对象   |



## org.devops.core.utils.util.FileTypeUtil

| 方法名      | 入参类型                                 | 描述               |
| ----------- | ---------------------------------------- | ------------------ |
| getFileType | InputStream inputStream                  | 获取文件 MediaType |
| getFileType | InputStream inputStream, String fileName | 获取文件 MediaType |



## org.devops.core.utils.util.FileUtil

| 方法名              | 入参类型                                               | 描述                                                 |
| ------------------- | ------------------------------------------------------ | ---------------------------------------------------- |
| writeIn             | String filename, String str                            | UTF8编码写入文件                                     |
| writeIn             | String filename, String str, String enc                | 指定编码写入文件                                     |
| mkDir               | String DirectoryName                                   | 创建一个目录                                         |
| readOnLine          | String filename                                        | 读取文件内容到一行                                   |
| readOnLines         | String filename                                        | 读取文件内容到多行                                   |
| deleteFile          | String filename                                        | 删除指定文件                                         |
| copyFile            | String sourceFile, String destDir, String newFileName  | 文件名拷贝文件                                       |
| copyFile            | InputStream source, String destDir, String newFileName | 输入流拷贝文件                                       |
| getFileSuffix       | String fileName                                        | 获取文件的后缀，没有后缀的直接返回空字符             |
| getFileSuffixNoDian | String fileName                                        | 获取文件的后缀（不包括 .），没有后缀的直接返回空字符 |
| deleteDir           | String directory                                       | 删除目录，先递归删除里面得文件                       |



## org.devops.core.utils.util.HttpUtils

| 方法名            | 入参类型                                                     | 描述                                           |
| ----------------- | ------------------------------------------------------------ | ---------------------------------------------- |
| setConnectTimeout | int timeout                                                  | 设置连接超时                                   |
| setReadTimeout    | int timeout                                                  | 设置读超时                                     |
| setTimeoutDefault |                                                              | 恢复默认超时                                   |
| doGet             | String url, MultiValueMap<String, String> headerMap          | 带头部的GET请求                                |
| doGet             | String url, Map<String, Object> param                        | 带参数的GET请求                                |
| doGet             | String url, Map<String, Object> param, MultiValueMap<String, String> headerMap | 带参数和头部的GET请求                          |
| doHttpGet         | String url                                                   | 默认超时时间的GET请求                          |
| doHttpGet         | String url, MultiValueMap<String, String> headerMap          | 默认超时时间的GET请求（带头部）                |
| doHttpGet         | String url, Map<String, Object> param                        | 默认超时时间的GET请求（带参数）                |
| doHttpGet         | String url, MultiValueMap<String, String> headerMap, int connectionTimeOut, int readTimeOut | HTTP GET请求                                   |
| doPost            | String url                                                   | POST请求                                       |
| doPost            | String url, Map<String, Object> param                        | 带参数的POST请求                               |
| doPost            | String url, Map<String, Object> param, MultiValueMap<String, String> headerMap | 带参数和头部的POST请求                         |
| doPost            | String url, Object body                                      | 带请求体的POST请求                             |
| doPost            | String url, Map<String, Object> param, Object body           | 带参数和请求体的POST请求                       |
| doPost            | String url, MultiValueMap<String, String> headerMap, Object body | 带请求体和头部的POST请求                       |
| doHttpPost        | String url                                                   | 默认超时时间的POST请求                         |
| doHttpPost        | String url, MultiValueMap<String, String> headerMap          | 默认超时时间的POST请求（带头部）               |
| doHttpPost        | String url, Object body                                      | 默认超时时间的POST请求（带请求体）             |
| doHttpPost        | String url, Map<String, Object> param                        | 默认超时时间的POST请求（带参数）               |
| doHttpPost        | String url, Map<String, Object> param, Object body           | 默认超时时间的POST请求（带参数和请求体）       |
| doHttpPost        | url, MultiValueMap<String, String> headerMap, Object body    | 默认超时时间的POST请求（带头部和请求体）       |
| doHttpPost        | String url, MultiValueMap<String, String> headerMap, Map<String, Object> param, Object body | 默认超时时间的POST请求（带头部、参数和请求体） |
| doHttpPost        | String url, MultiValueMap<String, String> headerMap, Object body, int connectionTimeOut, int readTimeOut | HTTP POST请求                                  |
| doGraphPost       | String url, Map<String, Object> map                          | 后端取累加图表数据，超时时间设置为10分钟       |
| upload            | String urlString, String fileName, byte[] fileByte           | 上传                                           |
| download          | String url                                                   | 下载（返回输入流）                             |
| downloadToByte    | String url                                                   | 下载（返回输字节数组）                         |
| downloadToFile    | String url, String toPath                                    | 下载到本地文件                                 |



## org.devops.core.utils.util.IntUtil

| 方法名         | 入参类型                       | 描述                       |
| -------------- | ------------------------------ | -------------------------- |
| isZero         | Object value                   | 判断值是否0                |
| toInt          | Integer value                  | Integer转为int             |
| toInt          | Integer value, int defValue    | Integer转为int（带默认值） |
| toInteger      | Integer value                  | Integer换Integer           |
| toInteger      | String value                   | String转Interger           |
| toInt          | Object value                   | Object转Int                |
| isNotZero      | Integer value                  | 判断值是否不为0            |
| returnInt      | Double value                   | Double转int                |
| returnInt      | double value                   | double转int                |
| isInteger      | String str                     | 判断字符串是否为整数       |
| compareInteger | Integer value1, Integer value2 | 判断两个值是否相等         |



## org.devops.core.utils.util.IPUtil

| 方法名      | 入参类型                   | 描述                          |
| ----------- | -------------------------- | ----------------------------- |
| getLocalIP  |                            | 获取本地IP地址                |
| ipv4ToLong  | String strIp               | ip地址转成long型数字          |
| longToIpv4  | long longIP                | 根据long值获取ip              |
| getIpAddr   | HttpServletRequest request | 获取HTTP请求的IP地址          |
| getIpRegion | String ip                  | 使用ip2region库查询ip所在地址 |



## org.devops.core.utils.util.JodaTimeUtil

| 方法名              | 入参类型                                           | 描述                                       |
| ------------------- | -------------------------------------------------- | ------------------------------------------ |
| getYYMMdd           |                                                    | 获取6位日期 yyMMdd                         |
| getYYYYMMDD         |                                                    | 获取8位日期 yyyyMMdd                       |
| getYYMMddHHMMSS     |                                                    |                                            |
| getyyMMddHHmms      |                                                    | 获取12位日期 yyMMddHHmmss                  |
| getCurHHmm          |                                                    | 获取当天的时分 HH:mm                       |
| getCurTime          |                                                    | 获取当天的时间 HH:mm:ss                    |
| getCurDate          |                                                    | 获取当天的日期 yyyy-MM-dd                  |
| getCurDateTime      |                                                    | 获取当天的日期时间 yyyy-MM-dd HH:mm:ss     |
| getCurDateHHmm      |                                                    | 获取当天的日期时分 yyyy-MM-dd HH:mm        |
| getYearMonth        |                                                    | 获取年月 yyyy-MM                           |
| getYearMonth        | DateTime dateTime                                  | 获取指定时间年月 yyyy-MM                   |
| getHour             |                                                    | 获取小时                                   |
| betweenDate         | String start_time, String end_time                 | 两个日期比较前后 yyyy-MM-dd                |
| betweenDateHHmm     | String start_time, String end_time                 | 两个日期时间比较前后 yyyy-MM-dd HH:mm      |
| betweenDateTime     | String start_time, String end_time                 | 两个日期时间比较前后 yyyy-MM-dd HH:mm:ss   |
| betweenTime         | String start_time, String end_time                 | 两个时间比较前后 HH:mm:ss                  |
| betweenHHmm         | String start_time, String end_time                 | 两个日期时分比较前后 HH:mm                 |
| dateTimeInToday     | String dateTime                                    | 日期时间是否在今天之内 yyyy-MM-dd HH:mm:ss |
| timeInToday         | String time                                        | 时间是否在今天之内 HH:mm:ss                |
| daysThan2Today      | int days（正数是算之后的时间，负数是算之前的时间） | 推算前后天数的日期 yyyy-MM-dd              |
| hoursThan2Today     | int hours                                          | 推算前后小时的时间 HH:mm:ss                |
| secondsThan2Today   | int seconds                                        | 推算前后秒的时间 HH:mm:ss                  |
| StringToDate        | String date_time                                   | 字符转日期格式类型 yyyy-MM-dd HH:mm:ss     |
| StringToDate        | String date_time, String format                    | 字符转日期格式类型                         |
| StringToYYYYMMDD    | String date_time                                   | 字符转日期格式类型 yyyy-MM-dd              |
| StringToYYYYMM      | String date_time                                   | 字符转日期格式类型 yyyy-MM                 |
| todayDate           |                                                    | 获取今天的日期                             |
| todayInStartEnd     | String start_time, String end_time                 | 判断今天时间在两个时间之间                 |
| minutesThanDateTime | String date_time, int minutes                      | 开始时间前多少分钟的时间                   |
| dateSplit           | Date start_date, Date end_date                     | 获取两个日期的列表                         |



## org.devops.core.utils.util.ListUtil

| 方法名          | 入参类型                                     | 描述                                       |
| --------------- | -------------------------------------------- | ------------------------------------------ |
| getCurveValue   | List<Long> list                              | 计算集合中的最大值和最小值                 |
| isNotNull       | List<?> list                                 | 判断List是否不为空                         |
| isNUll          | List<?> list                                 | 判断List是否为空                           |
| removeDuplicate | List<T> list                                 | 去除集合中重复的内容                       |
| toStringList    | List<Long> list                              | long列表转换为string列表                   |
| toStringList    | List<T> list, String field                   | 遍历列表抽取字段（String类型）             |
| toIntegerList   | List<T> list, String field                   | 遍历列表抽取字段（Integer类型）            |
| toLongList      | List<T> list, String field                   | 遍历列表抽取字段（Long类型）               |
| toLongList      | List<String> list                            | string列表转换为long列表                   |
| toIntegerList   | String list                                  | 字符串转换为Integer列表                    |
| toLongList      | String ids                                   | 字符串转换为Long列表                       |
| toList          | String ids, Class<T> clazz                   | 字符串转换为列表                           |
| toIntegerList   | String[] ids                                 | 数组转换为Integer列表                      |
| toList          | String[] ids, Class<T> clazz                 | 数组转换为列表                             |
| toIntegerList   | List<String> ids                             | string列表转换Integer列表                  |
| toList          | Set<T> idSet                                 | Set转成List                                |
| join            | String[] strs, String split                  | 把数组拼接成字符串                         |
| join            | List<String> list, String split              | 把列表拼接成字符串                         |
| join            | Object[] arr, String split                   | 把数组拼接成字符串                         |
| join            | Object[] arr                                 | 把数组拼接成字符串，默认英文逗号           |
| join            | List<String> list                            | 把列表拼接成字符串，默认英文逗号           |
| replaceKeys     | List<String> list, String open, String close | 增加前后符号                               |
| size            | List list                                    | 获取列表长度                               |
| limit           | List<T> list, int start, int size            | 列表截取，分页                             |
| filter          | List<T> list, T f                            | 过滤list中的f                              |
| filterOne       | List<T> list, String fieldName, Object value | 获取list中fieldName字段值等于value的一项   |
| filter          | List<T> list, String fieldName, Object value | 获取list中fieldName字段值等于value的所有项 |
| differenceSet   | List<T> source, List<T> list                 | 取差集                                     |
| get             |                                              | 初始化列表                                 |
| desc            | List<T> list, String field                   | 列表倒序                                   |
| sort            | List<T> list, String fieldName, String type  | 列表排序                                   |
| partition       | List<T> list, int size                       | 列表分割，分段                             |



## org.devops.core.utils.util.LongUtil

| 方法名    | 入参类型      | 描述            |
| --------- | ------------- | --------------- |
| isZero    | Long value    | 判断值是否为0   |
| isNotZero | Long value    | 判断值是否不为0 |
| toLong    | Long value    | Long转long      |
| toLong    | Integer value | Integer转long   |
| toLong    | Object value  | Object转long    |
| toLong    | String value  | String转long    |



## org.devops.core.utils.util.MapSortUtil

| 方法名                  | 入参类型      | 描述             |
| ----------------------- | ------------- | ---------------- |
| sortByValue             | Map<K, V> map | 根据值排序，正序 |
| sortByValueReverseOrder | Map<K, V> map | 根据值排序，倒叙 |
| sortByKey               | Map<K, V> map | 根据键排序，正序 |
| sortByKeyReverseOrder   | Map<K, V> map | 根据键排序，倒叙 |



## org.devops.core.utils.util.MapUtil

| 方法名                       | 入参类型                                  | 描述                         |
| ---------------------------- | ----------------------------------------- | ---------------------------- |
| toMap                        | List<T> list, String field, Class<T2> cla | 将list转换为map              |
| toMap                        | List<T> list, String field                | 将list转换为Long类型的map    |
| toMapLong                    | List<T> list, String field                | 将list转换为Long类型的map    |
| toMapInteger                 | List<T> list, String field                | 将list转换为Integer类型的map |
| toMapString                  | List<T> list, String field                | 将list转换为String类型的map  |
| toMapList                    | List<T> list, String field                | 将list分组                   |
| groupByString                | List<T> list, String field                | 将list分组                   |
| groupByLong                  | List<T> list, String field                | 将list分组                   |
| getList                      | Map<T1, List<T2>> map, T1 key             | 获取map值                    |
| toMapResultIncludeFields     | Object source, String... includeFields    | 对象转为map                  |
| toMapResultExcludeFields     | Object source, String... excludeFields    | 对象转为map                  |
| toMapListResultIncludeFields | List source, String... includeFields      | 对象list转为map list         |
| toMapListResultExcludeFields | List source, String... excludeFields      | 对象list转为map list         |



## org.devops.core.utils.util.MD5

| 方法名        | 入参类型      | 描述                       |
| ------------- | ------------- | -------------------------- |
| getMD5        | String source | 字符串加密                 |
| getMD5        | byte[] source | 字节数组加密（返回String） |
| getBase64Code | byte[] source |                            |
| getMD5Byte    | byte[] source | 字节数组加密（返回byte[]） |



## org.devops.core.utils.util.PathUtil

| 方法名   | 入参类型                      | 描述 |
| -------- | ----------------------------- | ---- |
| PathUtil | String source, String... args |      |



## org.devops.core.utils.util.QRCodeUtils

| 方法名                          | 入参类型                                                     | 描述                        |
| ------------------------------- | ------------------------------------------------------------ | --------------------------- |
| toBufferedImage                 | String content, int width, int height                        | 返回一个 BufferedImage 对象 |
| writeToStream                   | String content, int width, int height                        | 将二维码图片输出到一个流中  |
| writeToStream                   | String content, OutputStream stream, int width, int height   | 将二维码图片输出到一个流中  |
| createQRCode                    | String content, String path, int width, int height           | 生成二维码图片文件          |
| generateQRCode                  | String url, HttpServletResponse response                     | 生成二维码图片文件          |
| generateQRCode                  | String url, HttpServletResponse response, int height, int width | 生成二维码图片文件          |
| drawStringWithFontStyleLineFeed | Graphics g, String strContent, int locX, int locY, Font font, int rowWidth, Color color | 开始绘制文字                |



## org.devops.core.utils.util.RandomUtil

| 方法名            | 入参类型 | 描述               |
| ----------------- | -------- | ------------------ |
| getFourRandNumber |          | 生成四位随机数     |
| getSixRandNumber  |          | 生成六位随机数     |
| getFourRandChar   |          | 生成四位随机字符   |
| getSixRandChar    |          | 生成六位随机字符   |
| getRandChars      | long len | 生成指定长度字符串 |



## org.devops.core.utils.util.ReflectionUtil

| 方法名     | 入参类型    | 描述                         |
| ---------- | ----------- | ---------------------------- |
| getClasses | String pack | 从包package中获取所有的Class |



## org.devops.core.utils.util.RequestUtil

| 方法名                 | 入参类型                                     | 描述         |
| ---------------------- | -------------------------------------------- | ------------ |
| getDomain              | HttpServletRequest request                   | 获取域名     |
| getRequestQueryAsMap   | HttpServletRequest request                   | 获取请求参数 |
| getRequestBodyAsMap    | HttpServletRequest request                   | 获取请求体   |
| getRequestBodyAsObject | HttpServletRequest request, Class<T> clazz   |              |
| getRequestBodyAsString | HttpServletRequest request                   |              |
| writeToResponse        | HttpServletResponse response, String content |              |
| writeToResponse        | HttpServletResponse response, Object content |              |



## org.devops.core.utils.util.ResourceUtil

| 方法名               | 入参类型    | 描述             |
| -------------------- | ----------- | ---------------- |
| readResourceAsStream | String path | 读取资源文件流   |
| readResourceAsString | String path | 读取资源文件文本 |



## org.devops.core.utils.util.RSAEncryptUtils

| 方法名     | 入参类型                      | 描述           |
| ---------- | ----------------------------- | -------------- |
| genKeyPair |                               | 随机生成密钥对 |
| encrypt    | String str, String publicKey  | RSA公钥加密    |
| decrypt    | String str, String privateKey | RSA私钥解密    |



## org.devops.core.utils.util.SignUtil

| 方法名        | 入参类型                      | 描述                  |
| ------------- | ----------------------------- | --------------------- |
| getSignString | Object param                  | 获取签名后的串        |
| getSignString | Map<String, Object> paramMaps | 获取签名后的串        |
| getUrlParams  | Map<String, Object> map       | 获取拼接的字符串      |
| getSignByMD5  | Object param,String suffix    | 利用md5获取签名后的串 |



## org.devops.core.utils.util.SortUtil

| 方法名                | 入参类型               | 描述 |
| --------------------- | ---------------------- | ---- |
| getSortWeight         | Object dao, Object svo |      |
| getListSortWeight     | Object dao, Object svo |      |
| getLastSortWeight     | Object dao, Object svo |      |
| getLastListSortWeight | Object dao, Object svo |      |
| getFirstSortWeight    | Object dao, Object svo |      |
| getAddSortWeight      | Object dao, Object svo |      |
| updateSortWeight      | Object dao, Object svo |      |



## org.devops.core.utils.util.StringUtil

| 方法名                | 入参类型                         | 描述                                                |
| --------------------- | -------------------------------- | --------------------------------------------------- |
| deleteTail            | String content, String str       | 删除字符                                            |
| find                  | String str, String s1            | 在 str 中查找 s1 出现的次数                         |
| getDefault            | String str, String defaultValue  | 字符串为空时返回默认值，否则返回字符串本身          |
| getSimpleString       | String string                    | 过滤特殊字符，值只能是[a-zA-Z0-9]                   |
| isEmpty               | Object str                       | 判断字符串是否为 null 或 空                         |
| isNotEmpty            | Object str                       | 判断字符串是否不为 null 或 空                       |
| isTrimEmpty           | String str                       | 经过 trim 后是否为空                                |
| listToString          | List list                        | 把list转换为一个用逗号分隔的字符串                  |
| join                  | String[] strs, String split      | 把数组拼接成字符串                                  |
| join                  | List<String> list, String split  | 把列表拼接成字符串                                  |
| join                  | Object[] arr                     | 把数组拼接成字符串，默认以英文逗号分割              |
| join                  | List<String> list                | 把列表拼接成字符串，默认以英文逗号分割              |
| getNotNull            | String str                       | 如果 str 为 null，返回空字符串，否则返回 str        |
| parseDouble           | String str                       | string 转 double                                    |
| parseInt              | String str                       | string 转 int                                       |
| parseLong             | String str                       | string 转 long                                      |
| subString             | String str, int len              | 获得字符串的子字符串，考虑中文为 2 个字符           |
| subString             | String str, int start, int len   | 获得字符串的子字符串，考虑中文为 2 个字符           |
| joinString            | String strs[]                    | 转换成 '1','2','3' 这样的形式                       |
| toInts                | String content                   | 分割为 int 数组，默认以英文逗号分隔                 |
| toInts                | String content, String split     | 分割为 int 数组                                     |
| split                 | String str                       | 将字符串分解成字符串数组                            |
| patchMa               | String content, String separator | 通过正则分割                                        |
| fixed                 | int sourceData, int length       | 数字前补0                                           |
| isEmail               | String email                     | 判断是否为邮箱                                      |
| getByteLength         | String s                         | 获得一个字符串有多少个字节                          |
| fullToHalf            | String str                       | 全角转半角                                          |
| halfToFull            | String str                       | 半角转全角                                          |
| addBlankWord          | String input, int length         | input如果长度不够length则未尾加空格补齐到length长度 |
| strToAscii            | String str                       | 把字符串转换成ascii码                               |
| strToAscii            | String str, String fix           | 把字符串转换成ascii码，fix为分割符                  |
| asciiToStr            | String ascii, String fix         | ASCII码转字符串，fix为分隔符                        |
| toAsciiString         | String str                       | 将中文进行ASCII编码                                 |
| toNormalString        | String str                       | 将ASCII码字符串转换成中文                           |
| toString              | String[] str, String separator   | 数组转字符串                                        |
| isNumber              | String str                       | 判断字符串是否为纯数字                              |
| getInt                | String str, int defaultValue     | string 转 int                                       |
| trans                 | String str                       | 如果 str 为 null 或长度为 0，返回"无"，否则返回 str |
| getIntFormString      | String str                       | 使用正则表达式获取字符串中的数字                    |
| compareString         | String strOne, String strTwo     | 判断两个字符串是否相等                              |
| exchange              | String[] change                  | 数组反转                                            |
| swap                  | String[] change, int x, int y    | 交换数组指定位置的值                                |
| replaceBlank          | String str                       | 替换字符串中的空字符                                |
| splitBlank            | String str                       | 空字符串分割数组                                    |
| toUCase               | String str                       | 字符串首字符大写，"abc" -> "Abc"                    |
| toLCase               | String str                       | 字符串首字符小写，"Abc" -> "abc"                    |
| toHHCase              | String str                       | "abc_xyz" -> "AbcXyz"                               |
| toLHCase              | String str                       | 字符串转驼峰，"abc_xyz" -> "abcXyz"                 |
| toUUCase              | String str                       | "AbcXye" -> "abc_xyz"                               |
| object2String         | String str                       | object 转 string                                    |
| appendToString        | Object... args                   | 字符串拼接                                          |
| jsonStringToLowerCase | String json                      | json字符串首字母大写变成小写                        |



## org.devops.core.utils.util.TimeUtil





## org.devops.core.utils.util.UrlUtil

| 方法名   | 入参类型                  | 描述                 |
| -------- | ------------------------- | -------------------- |
| uriToMap | String url                | 解析URL地址参数      |
| mapToUri | Map<String, String> param | 拼接map为URL参数形式 |



## org.devops.core.utils.util.ZipUtil

| 方法名 | 入参类型                                                     | 描述             |
| ------ | ------------------------------------------------------------ | ---------------- |
| unPack | String zipFileFullPath, String unzipfilePath                 | 解压到对应目录下 |
| toZip  | String srcDir, String targeFullPath, boolean KeepDirStructure | 压缩成ZIP        |

