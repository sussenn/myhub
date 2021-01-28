package cn.xdf.seckill.seckill.pojo;

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
 * 
 * </p>
 *
 * @author sussenn
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbSecKillGoods对象", description="")
public class TbSecKillGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "spu ID")
    private String supId;

    @ApiModelProperty(value = "sku ID")
    private String skuId;

    @ApiModelProperty(value = "标题")
    private String name;

    @ApiModelProperty(value = "商品图片")
    private String image;

    @ApiModelProperty(value = "原价格")
    private Long price;

    @ApiModelProperty(value = "秒杀价格")
    private Long costPrice;

    @ApiModelProperty(value = "审核日期")
    private Date checkTime;

    @ApiModelProperty(value = "审核状态，0未审核，1审核通过，2审核不通过")
    private String status;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "秒杀商品数")
    private Integer num;

    @ApiModelProperty(value = "剩余库存数")
    private Integer stockCount;

    @ApiModelProperty(value = "描述")
    private String introduction;

    @ApiModelProperty(value = "创建日期")
    private Date gmtCreate;


}
