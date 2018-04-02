package com.ruifucredit.cloud.commodity.service;

import java.util.List;

import com.ruifucredit.cloud.commodity.support.dto.Goods;

public interface IGoodsService {
	
	Goods query(Long id);

	Goods create(Goods goods);

	List<Goods> query(String name);
	
}
