package com.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bean.Goods;
import com.util.UUIDUtil;

public class GoodsService {
	private static List<Goods> list = new ArrayList<Goods>();
	private static String FILEPATH="F:\\workspace2018\\MeiTuan\\WebContent\\image";
	static {
		getFiles(FILEPATH);
	}
	public static void getFiles(String filePath) {
		int i=1;
		int type=1;
		File root = new File(filePath);
		File[] files = root.listFiles();
		for(File file:files) {
			if(file.isDirectory()) {
				getFiles(file.getAbsolutePath());
			}else {
				if(type==6)
					type=0;
				list.add(new Goods(UUIDUtil.getUUID(),file.getAbsolutePath(),i++*1.1+11.1,type++));
			}
		}
	}
	public static List<Goods> getListType(int type) {
		List<Goods> temp = new ArrayList<Goods>();
		for(int i=0;i<list.size();i++) {
			Goods goods = list.get(i);
			if(type==0) {
				temp.add(goods);
				continue;
			}
			if(goods.getType()==type) {
				temp.add(goods);
			}
		}
		return temp;
	}
}
