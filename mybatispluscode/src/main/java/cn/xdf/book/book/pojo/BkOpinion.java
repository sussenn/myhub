package cn.xdf.book.book.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 意见表
 * </p>
 *
 * @author sussenn
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BkOpinion对象", description="意见表")
public class BkOpinion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String uid;

    @ApiModelProperty(value = "用户姓名")
    private String username;

    @ApiModelProperty(value = "分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "版本号id")
    private Integer vid;

    @ApiModelProperty(value = "页码")
    private Integer page;

    @ApiModelProperty(value = "题目")
    private Integer topic;

    @ApiModelProperty(value = "内容描述")
    private String des;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "审核状态 0未审核/1审核通过/2无效意见")
    private String status;

    @ApiModelProperty(value = "提交时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "是否删除 0fales/1true")
    private Boolean isDeleted;


}
