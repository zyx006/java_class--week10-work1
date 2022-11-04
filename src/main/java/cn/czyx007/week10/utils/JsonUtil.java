package cn.czyx007.week10.utils;

import cn.czyx007.week10.bean.Student;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 张宇轩
 * @createTime : 2022/11/3
 */
public class JsonUtil {
    /**
     * 从json文件中加载数据
     * @return 所有不重复的学生数据的List集合
     */
    public static List<Student> loadDataFromJson() {
        List<Student> students = null;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/student.json"))) {
            StringBuilder jsonStr = new StringBuilder();
            String aLine;
            while ((aLine=br.readLine()) != null){
                jsonStr.append(aLine);
            }

            students = new GsonBuilder().create().fromJson(jsonStr.toString(), new TypeToken<ArrayList<Student>>(){}.getType());

            Map<String, List<String>> studentMap = new HashMap<>();
            for (int i = 0; i < students.size(); i++) {
                String id = students.get(i).getId();
                String lessonName = students.get(i).getLessonName();

                if (!StudentUtil.checkIfStudentScoreDuplicate(studentMap, id, lessonName)){
                    students.remove(i--);
                }
            }

            System.out.println("成功导入 " + students.size() + " 条学生数据");
            System.out.print("按任意键返回主菜单");
            System.in.read();
        } catch (Exception e) {
            System.out.println("数据导入失败！");
            e.printStackTrace();
        }
        return students;
    }

    /**
     * 导出学生成绩信息到json文件中
     * @param students 待导出成绩信息的学生数据的List集合
     */
    public static void exportDataToJson(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/score.json"))) {
            JsonArray jsonArray = new JsonArray();
            int studentCnt = 0;
            Map<String, Boolean> isWritten = new HashMap<>();
            for (int i = 0; i < students.size(); i++) {
                if (isWritten.containsKey(students.get(i).getId())) {
                    continue;
                }
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", students.get(i).getId());
                jsonObject.addProperty("name", students.get(i).getName());
                jsonObject.addProperty("gender", students.get(i).getGender());

                int lessonCnt = 0;
                double allScore = 0;
                for (Student stu : students) {
                    if (stu.getId().equals(students.get(i).getId())) {
                        lessonCnt++;
                        allScore += stu.getScore();
                    }
                }
                jsonObject.addProperty("total", allScore);
                jsonObject.addProperty("average", allScore / lessonCnt);

                jsonArray.add(jsonObject);
                studentCnt++;

                isWritten.put(students.get(i).getId(), true);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            bw.write(gson.toJson(jsonArray));
            System.out.println("成功导出 " + studentCnt + " 条学生成绩信息到 json 文件中");
            System.out.print("按任意键返回主菜单");
            System.in.read();
        } catch (Exception e) {
            System.out.println("数据导出失败！");
            e.printStackTrace();
        }
    }
}
