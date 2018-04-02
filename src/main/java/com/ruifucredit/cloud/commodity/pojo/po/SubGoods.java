package com.ruifucredit.cloud.commodity.pojo.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.experimental.Accessors;

/*
create table c_sub_goods (
    sub_goods_id integer primary key,
    goods_id integer,
    sub_name varchar2(128),
    goods_price number(10,5),
    create_time timestamp,
    update_time timestamp,
    sub_status varchar2(4)
);
create sequence c_sub_goods_seq;
 */
@Data
@Accessors(chain=true)
@Entity
@Table(name="c_sub_goods")
public class SubGoods {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sub_goods_id_generator")
	@SequenceGenerator(name="sub_goods_id_generator", sequenceName="c_sub_goods_seq", allocationSize=1)
	@Column(name="sub_goods_id")
	private Long subGoodsId;
	@Column(name="goods_id")
	private Long goodsId;
	@Column(name="sub_name")
	private String subName;
	@Column(name="goods_price")
	private BigDecimal goodsPrice;
	@Column(name="create_time")
	private Date createTime;
	@Column(name="update_time")
	private Date updateTime;
	@Column(name="sub_status")
	private String subStatus;
	
	public SubGoods() {
		super();
	}
	
	public SubGoods(com.ruifucredit.cloud.commodity.support.dto.SubGoods subGoods) {
		this();
		BeanUtils.copyProperties(subGoods, this);
	}
	
	public com.ruifucredit.cloud.commodity.support.dto.SubGoods subGoods() {

		com.ruifucredit.cloud.commodity.support.dto.SubGoods subGoods = 
				new com.ruifucredit.cloud.commodity.support.dto.SubGoods();

		BeanUtils.copyProperties(this, subGoods);
		
		return subGoods;
	}
	
}
