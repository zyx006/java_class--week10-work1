package cn.czyx007.week10.test;

import cn.czyx007.week10.bean.Student;
import cn.czyx007.week10.utils.ExcelUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class ExcelUtilTest {
    @Test
    public void testLoadDataFromExcel(){
        List<Student> students = ExcelUtil.loadDataFromExcel();
        students.forEach(System.out::println);
    }

    @Test
    public void testExportDataToExcel(){
        List<Student> students = ExcelUtil.loadDataFromExcel();
        ExcelUtil.exportDataToExcel(students);
    }
}
