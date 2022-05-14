# ie模块 - import export 导入导出功能
## 导出

## 开始使用
### 模块引入
  先引入parent
  ```xml
  <parent>
    <groupId>com.devops</groupId>
    <artifactId>devops-parent</artifactId>
    <version>6.0.0-SNAPSHOT</version>
  </parent>
  ```
  再引入相应的模块
  ```xml
  <dependency>
    <groupId>com.devops.core</groupId>
    <artifactId>devops-core-ie</artifactId>
  </dependency>
  ```

  ## 相关文档API
  TestExcelRowVO
  ```java
  public class TestExcelRowVO {

    public TestExcelRowVO(){

    }
    public TestExcelRowVO(Date a,String b,Integer c,Double d){
      this.dateCell = a;
      this.strCell = b;
      this.intCell = c;
      this.doubleCell = d;
    }
    
    //${R}是指自动
    @Excel(valuePosition="A${R}")
    private Date dateCell;
    
    @Excel(valuePosition="B${R}")
    private String strCell;
    
    @Excel(valuePosition="C${R}")
    private Integer intCell;
    
    @Excel(valuePosition="D${R}")
    private Double doubleCell;

    public Date getDateCell() {
      return dateCell;
    }

    public void setDateCell(Date dateCell) {
      this.dateCell = dateCell;
    }

    public String getStrCell() {
      return strCell;
    }

    public void setStrCell(String strCell) {
      this.strCell = strCell;
    }

    public Integer getIntCell() {
      return intCell;
    }

    public void setIntCell(Integer intCell) {
      this.intCell = intCell;
    }

    public Double getDoubleCell() {
      return doubleCell;
    }

    public void setDoubleCell(Double doubleCell) {
      this.doubleCell = doubleCell;
    }
    
    
  }
  ```
  TextExcelVO
  ```java
  @Excel(sheet="sheet1")
    public class TestExcelVO {

      @Excel(titlePosition="A1",title={"大标题1","大标题2","大标题3","大标题4"})
      private String titleA;
      
      @Excel(titlePosition="C2",title="小标题BA1")
      private String titleB;
      
      @Excel(titlePosition="B2")
      private String titleC;
      
      @Excel(titlePosition="B3",title="小标题B3",valuePosition="C3")
      private String titleD;
      
      @Excel(valuePosition="C4")
      private String valueA;
      
      @Excel(valuePosition="A5")
      private List<TestExcelRowVO> list;

      public String getTitleA() {
        return titleA;
      }

      public void setTitleA(String titleA) {
        this.titleA = titleA;
      }

      public String getTitleB() {
        return titleB;
      }

      public void setTitleB(String titleB) {
        this.titleB = titleB;
      }

      public String getTitleC() {
        return titleC;
      }

      public void setTitleC(String titleC) {
        this.titleC = titleC;
      }
      
      public String getTitleD() {
        return titleD;
      }

      public void setTitleD(String titleD) {
        this.titleD = titleD;
      }

      public List<TestExcelRowVO> getList() {
        return list;
      }

      public void setList(List<TestExcelRowVO> list) {
        this.list = list;
      }

      public String getValueA() {
        return valueA;
      }

      public void setValueA(String valueA) {
        this.valueA = valueA;
      }
    }
  ```
  ### 导出
  1. 导出为OutputStream流，结合HttpServletResponse发送到前端。
  ```java
    TestExcelVO exportVO = new TestExcelVO();

    exportVO.setTitleA("ttttttt1");
    exportVO.setTitleB("ttttttt2");
    exportVO.setTitleC("ttttttt3");
    exportVO.setTitleD("ttttttt4");
    exportVO.setValueA("vvvvvvv1");
    

    List<TestExcelRowVO> listRow = new ArrayList<TestExcelRowVO>();
    listRow.add(new TestExcelRowVO(new Date(),"1",1,1.1));
    listRow.add(new TestExcelRowVO(new Date(),"2",2,2.2));
    listRow.add(new TestExcelRowVO(new Date(),"3",3,3.3));
    exportVO.setList(listRow);

    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try {
      ExcelUtil.export(exportVO, os);
      byte[] bytes = os.toByteArray();
      response.reset();
      response.setContentType("application/msexcel;charset=utf-8");
      response.setHeader("Content-disposition", "attachment;filename= "+ "test.xls"); 
      response.getOutputStream().write(bytes);
      response.getOutputStream().flush();
      response.getOutputStream().close();
      os.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  ```
  2. 导出为文件
  ```java
    TestExcelVO exportVO = new TestExcelVO();

    exportVO.setTitleA("ttttttt1");
    exportVO.setTitleB("ttttttt2");
    exportVO.setTitleC("ttttttt3");
    exportVO.setTitleD("ttttttt4");
    exportVO.setValueA("vvvvvvv1");
    

    List<TestExcelRowVO> listRow = new ArrayList<TestExcelRowVO>();
    listRow.add(new TestExcelRowVO(new Date(),"1",1,1.1));
    listRow.add(new TestExcelRowVO(new Date(),"2",2,2.2));
    listRow.add(new TestExcelRowVO(new Date(),"3",3,3.3));
    exportVO.setList(listRow);

    ExcelUtil.export(exportVO, "/data/temp/test.xls");
  ```
  ### 导入
  1. 导入为导出的逆过程，复杂结构，如上面导出的那种
  ```java
    TestExcelVO vo = parseExcel("/data/temp/test.xls", TestExcelVO.class);
  ```
  2. 简单结构
  ```java
    @Excel(sheet="商品目录",beginRow=1)  //beginRow代表从第一行开始，第0行一般是文字标题
    public class Item extends BaseBean{
      @Excel(valueTitle="商品名")  //A方式，只要有标题都能适用，建议使用这种方式
	    private String name;

      @Excel(valuePosition="G${R}") //B方式通过定位，适合模板不会经常改动
	    private String name;
    }

    Item vo = parseExcel("/data/temp/test.xls", Item.class);
  ```