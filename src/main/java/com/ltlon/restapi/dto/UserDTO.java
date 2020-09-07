package com.ltlon.restapi.dto;

import com.ltlon.restapi.annotation.Sex;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @program: REST_API_DEMO
 * @description:
 * @author: LTL
 * @create: 2019-11-21 11:14
 **/
@Data
public class UserDTO {

    /**
     定义新增校验组（新增ID为空）
     *
     */
    public interface Add{}

    /**
     * 定义更新校验组（更新ID不为空）
     */
    public interface Update{}

    /***用户id***/
    @Null(groups = Add.class,message = "新增用户时,用户ID应为为空!")
    @NotNull(groups = Update.class,message = "更新用户时,用户ID不应为空!")
    private Integer userId;
    /***用户姓名***/
    @NotBlank(groups = {Add.class,Update.class},message = "用户姓名不能为空")
    private String userName;

    /***用户性别***/
    @Sex(groups = {Add.class,Update.class})
    private String sex;
    /***用户年龄***/
    @Min(groups = {Add.class,Update.class},value = 18,message = "年龄必须大于等于18岁!")
    private int age;

    private String phone;
    @Email(groups = {Add.class,Update.class},message="邮箱格式错误")
    private String email;

}
