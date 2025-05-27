package com.design.year.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cover")
public class Cover {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer num;
    private String percent;
    private Integer regionid;
    private String region;
    private Integer years;
}
