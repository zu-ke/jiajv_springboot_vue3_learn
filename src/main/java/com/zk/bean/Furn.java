package com.zk.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Furn {

    /*解读：根据自己的需求配置表单校验 JSR303*/

    /*解读：标识主键，并且设置自增长*/
    @TableId(type = IdType.AUTO)
    private Integer id;
    /*解读：如果是对字符串进行非空校验，使用@NotEmpty*/
    @NotEmpty(message = "请输入家居名")
    private String name;
    @NotEmpty(message = "请输入厂商名")
    private String maker;
    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "价格不能小于0")
    private Double price;
    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "数量不能小于0")
    private Integer sales;
    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "数量不能小于0")
    private Integer stock;
}
