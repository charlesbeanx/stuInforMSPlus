package com.charles.utils;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.sql.DataSource;
/**
 * @author charlesBean
 * @create 2019-03-05 15:33
 * @email charlesbean@aliyun.com
 * @path stuInforMS/com.charles.autoCreateCode/null.java
 */
public class AutoCreateCode {
    /**
     *
     */
    @Autowired
    DataSource ds;
    public void autoCC(){
        String[] strings = new String[]{
                "course",
                "department",
                "employee",
                "grade",
                "grade_course",
                "loginlog",
                "permission",
                "role",
                "role_permission",
                "score",
                "student",
                "teacher",
                "user",
                "user_role"
        };
        for (String string : strings) {
            this.autoCreateCodePlus(string);
        }
    }


    public void autoCreateCodePlus(String tableName){
        //1 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(false)//是否支持AR模式，活动记录
                .setAuthor("charlesBean")//作者
                .setOutputDir("D:\\Java\\workspace\\Idea\\stuInforMS\\src\\main\\java")//生成之后的存储路口
                .setFileOverride(false)//是否文件覆盖
                .setIdType(IdType.AUTO)//主键策略
                 .setBaseResultMap(true)
                .setBaseColumnList(true);
        //2 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://47.107.241.29:3306/mathInforMS?useUnicode=true&characterEncoding=utf8")
                .setUsername("root")
                .setPassword("2015xiazhi");
        //3 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("mims_")
                .setInclude("mims_"+tableName);
        //4 设置包名策略
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.charles")
                .setEntity("bean")
                .setMapper("mapper")
                .setService("service")
                .setController("web")
                .setXml("mapper");
        //5 整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);
        //6 执行
        System.out.println("已经进入逆向声场代码的执行过程中，请稍后~~~~~~~");
        autoGenerator.execute();
    }

}
