package cn.czyx007.week10.test;

import cn.czyx007.week10.bean.Student;
import cn.czyx007.week10.utils.JsonUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class JsonUtilTest {
    @Test
    public void testLoadDataFromJson(){
        List<Student> students = JsonUtil.loadDataFromJson();
        students.forEach(System.out::println);
    }

    @Test
    public void testExportDataToJson(){
        List<Student> students = JsonUtil.loadDataFromJson();
        JsonUtil.exportDataToJson(students);
    }
}
