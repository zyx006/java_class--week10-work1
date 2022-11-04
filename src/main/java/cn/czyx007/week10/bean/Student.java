package cn.czyx007.week10.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    /**
     * 学号
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 课程名称
     */
    private String lessonName;

    /**
     * 成绩
     */
    private Double score;
}
