package com.ruifucredit.cloud.commodity.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruifucredit.cloud.commodity.service.IGoodsService;
import com.ruifucredit.cloud.commodity.support.controller.IGoodsController;
import com.ruifucredit.cloud.commodity.support.dto.Goods;
import com.ruifucredit.cloud.kit.dto.Outcoming;

import lombok.SneakyThrows;

@RestController
public class GoodsController implements IGoodsController {
	
	private Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Autowired
	private IGoodsService goodsService;
	
	@SneakyThrows
	public Outcoming<Goods> queryGoods(@PathVariable(name="id") Long id) {
		
		Goods result = goodsService.query(id);
		
		logger.info("GoodsController.queryGoods.id: {}, result: {}", id, result);
		
		Thread.sleep(new Random(new Date().getTime()).nextInt(5)*1000);
		
		return new Outcoming<Goods>().setCode(Outcoming.OK_CODE).setResult(result);
		
	}
	
	public Outcoming<List<Goods>> queryGoods(@RequestParam(name="goodsName") String name) {
		
		List<Goods> result = goodsService.query(name);
		
		return new Outcoming<List<Goods>>(result);
		
	}
	
	/*
{"goodsName":"某种食品","goodsType":"F","goodsStatus":"A","subGoods":[{"subName":"好吃点","goodsPrice":10.5,"subStatus":"A"},{"subName":"多吃点","goodsPrice":9.8,"subStatus":"A"}]}
	 */
	public Outcoming<Goods> createGoods(@RequestBody Goods param) {
		
		Goods result = goodsService.create(param);
		
		return new Outcoming<Goods>(result);
	}
	
}
