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
public class User {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
