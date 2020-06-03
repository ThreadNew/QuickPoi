## QuickPoi    
### 简介
>quickpoi旨在简化项目中excel的导出，利用注解即可完成excel的导出   
### 使用 
##### 1、在pojo中添加注解
```java
public class UserInfo {
    @ExcelRow(title = "姓名",cellType = CellType.TextType,cellNum = 0,remove = true,rmvGroup = {1,2,3})
    private String name;
    @ExcelRow(title = "年龄",cellType = CellType.TextType,cellNum = 1)
    private int age;
    @ExcelRow(title = "地址",cellType = CellType.TextType,cellNum = 2)
    private String address;
    @ExcelRow(title = "日期",cellType = CellType.DateType,cellNum = 4,dateFormat = "yyyy-MM-dd",remove = true,rmvGroup = {1})
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
          int[] a={1};
        excelExportFactory.setRemove(true,a);//是否过滤某列
        //从数据库中查找的所要导出的数据集合
        excelExportFactory.setResult(list);
        //导出
        excelExportFactory.ExcelExport();
```    
### 讲解
- excelExportFactory.setRemove(true,a);主要作用：当一个pojo被复用时原来上面已经有了@ExcelRow注解；但实际这次要导出的excel要去掉某列属性，为了不影响先前的操作，所有引用了  @ExcelRow(title = "姓名",cellType = CellType.TextType,cellNum = 0,remove = true,rmvGroup = {1,2,3})中的remove和rmvGroup。
- remove：设置false时不去除，true时剔除该列
- rmvGroup的作用就是分组，当pojo被多次复用时肯定需要展示的列也会不同，所以为了解决这个问题，就为各种情况分组，根据情况剔除自己指定的组。
### 总结  
-  此项目目前只完成了注解版的导出，目前只写了SXSSF类型的导出，剩余的后面会陆续添加
- 此项目也可以供初学者借鉴使用
- 目前项目的框架设计不是很好，毕竟能力有限，其中有几点可以借鉴：注解的数据结构的设计，缓存的设置，枚举的使用


