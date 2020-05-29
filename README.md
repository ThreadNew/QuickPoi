## QuickPoi    
### 简介
>quickpoi旨在简化项目中excel的导出，利用注解即可完成excel的导出   
### 使用 
##### 1、在pojo中添加注解
```java
public class UserInfo {
    @ExcelRow(name="name",title = "姓名",cellType = CellType.TextType,cellNum = 0)
    private String name;
    @ExcelRow(name="age",title = "年龄",cellType = CellType.TextType,cellNum = 1)
    private int age;
    @ExcelRow(name="address",title = "地址",cellType = CellType.TextType,cellNum = 2)
    private String address;
    @ExcelRow(name="datetime",title = "日期",cellType = CellType.DateType,cellNum = 4,dateFormat = "yyyy-MM-dd")
    private Date datetime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}

```  

##### 2.在需要的导出excel的方法中添加如下代码：
```java
        //1.申明需要使用哪种类型来导出excel 这里就是使用的POI中的 SXSSF
        SXSSFExcel sxssfExcel = ExcelType.sxssfExcelInstance();
        //设置保存在内存中的行数，超过就刷新到磁盘中
        sxssfExcel.setRowAccessWindowSize(100);
        //2 new一个excel导出工厂对象
        ExcelExportFactory excelExportFactory=new ExcelExportFactory();
        //设置类型
        excelExportFactory.setExcelExport(sxssfExcel);
        //设置输出流
        excelExportFactory.setOut(out);
        //设置注解所在的类
        excelExportFactory.setTarget(UserInfo.class);
        //设置excel中的标题
        excelExportFactory.setTitle("ceshi");
        //从数据库中查找的所要导出的数据集合
        excelExportFactory.setResult(list);
        //导出
        excelExportFactory.ExcelExport();
```  
### 总结  
-  此项目目前只完成了注解版的导出，目前只写了SXSSF类型的导出，剩余的后面会陆续添加
- 此项目也可以供初学者借鉴使用
- 目前项目的框架设计不是很好，毕竟能力有限，其中有几点可以借鉴：注解的数据结构的设计，缓存的设置，枚举的使用


