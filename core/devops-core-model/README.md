# devops-core-model
## mysql封装

## 开始使用
### 模块引入
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
  <artifactId>devops-core-model</artifactId>
</dependency>
```

## 相关文档API
假设有一张表 user

|字段名|类型|是否主键|
|:----|:--|:--|
|id|bigint(20)|Y|
|user_name|varchar(20)|N|
|status|int(11)|N|
```java
@Table(value = "user",create=true,comment="用户表")
public class User {

  public final static String ID = "id";
  public final static String USER_NAME = "user_name";
  public final static String STATUS = "status"; 

  @Column(name = "id",jdbcType="bigint(20)",priKey=true,autoIncrement=true,comment="流水号")
  private Long id;
  @Column(name = "user_name",jdbcType="varchar(20)",notNull=true,def="''",comment="用户名")
  private String userName;
  @Column(name = "status",jdbcType="int(11)",notNull=true,def="0",comment="状态")
  private Integer status;
}
```
### 初始化model类
```java
@Repository
public UserRepository extends AbstractModelRepository<User,Long>{
}
```
1. 增

    单个插入，返回主键ID
    ```java
    User user = new User();
    userRepository.insert(user);
    ```
    批量插入，返回主键ID
     ```java
    User user = new User();
    List<User> list = new ArrayList<User>();
    list.add(user);
    userRepository.insert(list);
    ```
2. 删（没有删除条件的时候会报错，为了防止全表删除）

    单个删,根据主键删除
    ```java
    // 方法1
    userRepository.delete(1);
    // 方法2
    userRepository
      .where(User.ID,1)
      .delete();
    ```
    批量删,根据主键删除
    ```java
    // 方法1
    userRepository.delete(new ArrayList<Long>(){{add(1);}});
    // 方法2
    userRepository
      .where(User.ID,new ArrayList<Long>(){{add(1);}},ModelOperator.IN)
      .delete();
    ```
3. 改
    
    改单个,根据主键改
    ```java
    User user = new User();
    // 方法1
    userRepository.update(user);
    // 方法2
    userRepository
      .where(User.ID,1)
      .update(user);
    // 只更改其中某些字段
    userRepository
      .include(User.STATUS,User.USER_NAME)
      .where(User.ID,1)
      .update(user);
    ```
     批量改,根据主键改
     ```java
    User user = new User();
    List<User> list = new ArrayList<User>();
    list.add(user);
    userRepository.update(list);
    ```
4. 查

    使用where条件
    ```java
    //单个
    userRepository
      .where(User.ID,1)
      .get();
    //列表
    userRepository
      .where(User.ID,1)
      .list();
    //获取数量
    userRepository
      .where(User.ID,1)
      .count();
    //带其他条件
    userRepository
      .where(User.ID,1)
      .orderBy(User.ID,"DESC",User.STATUS,"ASC")
      .limit(0,20)
      .list();
    ```
    使用ModelWhere
    ```java
    ModelWhere mw = new ModelWhere();
    mw.add(User.ID,1);
    //单个
    userRepository
      .where(mw)
      .get();
    //列表
    userRepository
      .where(mw)
      .list();
    //获取数量
    userRepository
      .where(mw)
      .count();
    //带其他条件
    userRepository
      .where(mw)
      .orderBy(User.ID,"DESC",User.STATUS,"ASC")
      .limit(0,20)
      .list();
    ```
    其他复杂情况
    ```java
    //其他条件
    userRepository
      .where(User.ID,1,ModelOperator.xx)
      .get();
    //or 条件
    userRepository
      .where(User.ID,1)
      .where(User.status,1,ModelCondition.OR)
      .get();
    //自定义语句(能不用,就不要用)
    userRepository
      .where("(select * from user)",ModelOperator.PLAIN)
      .get();
    //返回一个其他class
    userRepository
      .where(User.ID,1,ModelOperator.xx)
      .get(XXXX.class);
    ```