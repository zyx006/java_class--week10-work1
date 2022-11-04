package cn.czyx007.week10.utils;

import cn.czyx007.week10.bean.Student;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class ExcelUtil {
    /**
     * 从excel文件中加载数据
     * @return 所有不重复的学生数据的List集合
     */
    public static List<Student> loadDataFromExcel() {
        List<Student> students = new ArrayList<>();

        Map<String, List<String>> studentMap = new HashMap<>();
        Workbook wb = null;
        try {
            wb = Workbook.getWorkbook(new File("src/main/resources/student.xls"));
            Sheet sheet = wb.getSheet(0);

            int cnt = 0;
            int rows = sheet.getRows();
            for (int i = 1; i < rows; i++) {
                String id = sheet.getCell(0,i).getContents();
                String name = sheet.getCell(1,i).getContents();
                String gender = sheet.getCell(2,i).getContents();
                String lessonName = sheet.getCell(3,i).getContents();
                Double score = Double.parseDouble(sheet.getCell(4,i).getContents());

                if(StudentUtil.checkIfStudentScoreDuplicate(studentMap, id, lessonName)){
                    students.add(new Student(id, name, gender, lessonName, score));
                    cnt++;
                }
            }

            System.out.println("成功导入 " + cnt + " 条学生数据");
            System.out.print("按任意键返回主菜单");
            System.in.read();
        } catch (Exception e) {
            System.out.println("数据导入失败！");
            e.printStackTrace();
        }finally {
            if (wb != null){
                try {
                    wb.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return students;
    }

    /**
     * 导出学生成绩信息到excel文件中
     * @param students 待导出成绩信息的学生数据的List集合
     */
    public static void exportDataToExcel(List<Student> students) {
        WritableWorkbook wb = null;
        try {
            wb = Workbook.createWorkbook(new File("src/main/resources/score.xls"));
            WritableSheet ws = wb.createSheet("学生成绩", 0);

            ws.addCell(new Label(0,0,"学号"));
            ws.addCell(new Label(1,0,"姓名"));
            ws.addCell(new Label(2,0,"性别"));
            ws.addCell(new Label(3,0,"总分"));
            ws.addCell(new Label(4,0,"平均分"));

            int studentCnt = 0;
            Map<String, Boolean> isWritten = new HashMap<>();
            for (int i = 0; i < students.size(); i++) {
                if(isWritten.containsKey(students.get(i).getId())){
                    continue;
                }

                ws.addCell(new Label(0,studentCnt+1,students.get(i).getId()));
                ws.addCell(new Label(1,studentCnt+1,students.get(i).getName()));
                ws.addCell(new Label(2,studentCnt+1,students.get(i).getGender()));

                int lessonCnt=0;
                double allScore=0;
                for (Student stu : students) {
                    if(stu.getId().equals(students.get(i).getId())){
                        lessonCnt++;
                        allScore += stu.getScore();
                    }
                }
                ws.addCell(new Label(3, studentCnt+1, Double.toString(allScore)));
                ws.addCell(new Label(4, studentCnt+1, Double.toString(allScore/lessonCnt)));
                studentCnt++;

                isWritten.put(students.get(i).getId(), true);
            }
            wb.write();
            System.out.println("成功导出 " + studentCnt + " 条学生成绩信息到 excel 文件中");
            System.out.print("按任意键返回主菜单");
            System.in.read();
        } catch (Exception e) {
            System.out.println("数据导出失败！");
            e.printStackTrace();
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
