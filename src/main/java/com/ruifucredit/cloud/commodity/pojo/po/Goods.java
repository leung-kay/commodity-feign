package com.ruifucredit.cloud.commodity.pojo.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.experimental.Accessors;

/*
create table c_goods (
    goods_id integer primary key,
    goods_name varchar2(128),
    goods_type varchar2(16),
    create_time timestamp,
    update_time timestamp,
    goods_status varchar2(4)
);
create sequence c_goods_seq;
 */
@Data
@Accessors(chain=true)
@Entity
@Table(name="c_goods")
public class Goods {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="goods_id_generator")
	@SequenceGenerator(name="goods_id_generator", sequenceName="c_goods_seq", allocationSize=1)
	@Column(name="goods_id")
	private Long goodsId;
	@Column(name="goods_name")
	private String goodsName;
	@Column(name="goods_type")
	private String goodsType;
	@Column(name="create_time")
	private Date createTime;
	@Column(name="update_time")
	private Date updateTime;
	@Column(name="goods_status")
	private String goodsStatus;
	@Transient
	private List<SubGoods> subGoodses;
	
	public Goods() {
		super();
	}
	
	public Goods(com.ruifucredit.cloud.commodity.support.dto.Goods goods) {
		this();
		
		BeanUtils.copyProperties(goods, this, "subGoodses");
		
		if (goods.getSubGoodses() != null) {
			subGoodses = new ArrayList<>(goods.getSubGoodses().size());
			for (com.ruifucredit.cloud.commodity.support.dto.SubGoods subGoods : goods.getSubGoodses()) {
				this.subGoodses.add(new SubGoods(subGoods));
			}
		}
	}
	
	public com.ruifucredit.cloud.commodity.support.dto.Goods goods() {
		com.ruifucredit.cloud.commodity.support.dto.Goods goods = 
				new com.ruifucredit.cloud.commodity.support.dto.Goods();
		
		BeanUtils.copyProperties(this, goods, "subGoodses");
		
		if (getSubGoodses() != null) {
			
			List<com.ruifucredit.cloud.commodity.support.dto.SubGoods> subGoodses = 
					new ArrayList<>(getSubGoodses().size());
			
			for (SubGoods subGoods : getSubGoodses()) {
				subGoodses.add(subGoods.subGoods());
			}
			
			goods.setSubGoodses(subGoodses);
		}
		
		return goods;
	}
	
}
