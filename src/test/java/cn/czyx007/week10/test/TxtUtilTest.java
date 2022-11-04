package cn.czyx007.week10.test;

import cn.czyx007.week10.bean.Student;
import cn.czyx007.week10.utils.TxtUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class TxtUtilTest {
    @Test
    public void testLoadDataFromTxt(){
        List<Student> students = TxtUtil.loadDataFromTxt();
        students.forEach(System.out::println);
    }

    @Test
    public void testExportDataToTxt(){
        List<Student> students = TxtUtil.loadDataFromTxt();
        TxtUtil.exportDataToTxt(students);
    }
}
