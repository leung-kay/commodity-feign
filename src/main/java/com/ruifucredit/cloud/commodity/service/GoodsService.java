package com.ruifucredit.cloud.commodity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruifucredit.cloud.commodity.repository.db.GoodsRepository;
import com.ruifucredit.cloud.commodity.repository.db.SubGoodsRepository;
import com.ruifucredit.cloud.commodity.support.dto.Goods;

@Service
public class GoodsService implements IGoodsService {

	@Autowired
	private GoodsRepository goodsRepo;
	@Autowired
	private SubGoodsRepository subGoodsRepo;

	@Override
	public Goods query(Long id) {
		com.ruifucredit.cloud.commodity.pojo.po.Goods goods = goodsRepo.findOne(id);

		if (goods != null) {
			List<com.ruifucredit.cloud.commodity.pojo.po.SubGoods> subGoodses = subGoodsRepo.findByGoodsId(id);
			goods.setSubGoodses(subGoodses);
		}

		return goods.goods();
	}

	@Override
	public List<Goods> query(String name) {
		
		List<com.ruifucredit.cloud.commodity.pojo.po.Goods> goodses = goodsRepo.findByGoodsName(name);
		
		List<Goods> result = new ArrayList<>(goodses.size());
		
		for(com.ruifucredit.cloud.commodity.pojo.po.Goods goods : goodses) {
			result.add(goods.goods());
		}
		
		return result;

	}

	@Override
	public Goods create(Goods param) {

		Date now = new Date();

		com.ruifucredit.cloud.commodity.pojo.po.Goods goods = new com.ruifucredit.cloud.commodity.pojo.po.Goods(param);

		// 防止更新goods
		goods.setGoodsId(null).setCreateTime(now).setUpdateTime(now);

		com.ruifucredit.cloud.commodity.pojo.po.Goods result = goodsRepo.save(goods);

		// 防止更新sub_goods
		if (goods.getSubGoodses() != null) {
			for (com.ruifucredit.cloud.commodity.pojo.po.SubGoods subGoods : goods.getSubGoodses()) {
				subGoods.setSubGoodsId(null).setGoodsId(result.getGoodsId()).setCreateTime(now).setUpdateTime(now);
			}
		}

		List<com.ruifucredit.cloud.commodity.pojo.po.SubGoods> subGoodses = subGoodsRepo.save(goods.getSubGoodses());

		result.setSubGoodses(subGoodses);

		return result.goods();
	}

}
