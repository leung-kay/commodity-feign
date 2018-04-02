package com.ruifucredit.cloud.commodity.repository.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruifucredit.cloud.commodity.pojo.po.SubGoods;

public interface SubGoodsRepository extends JpaRepository<SubGoods, Long> {
	
	List<SubGoods> findByGoodsId(Long id);
	
}
