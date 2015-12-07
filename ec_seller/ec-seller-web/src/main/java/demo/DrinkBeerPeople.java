package demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DrinkBeerPeople {
	
	/** 已喝啤酒总数量 */
	private int alreadyDrinkBeerCount;
	/** 当前手里未喝的啤酒数量 */
	private int beer;
	/** 当前剩余瓶盖数量 */
	private int cap;
	/** 当前剩余的酒瓶数量 */
	private int bottle;
	
	public static void main(String[] args) {
		//在这个世界上创建了一个人
		DrinkBeerPeople people = new DrinkBeerPeople();
		//这哥们花了10块钱，去这个世界上唯一的商店上买酒。
		people.buyBeersFirstByMoney(new BigDecimal(10));
		//这哥们儿开始喝酒
		people.drinkBeers();
		//首先自己先判断一下，能不能换酒了
		while(people.checkCanExchangeBeers()){
			//拿瓶盖和酒瓶去兑换酒
			people.exchangeBeers();
			//继续喝酒
			people.drinkBeers();
		}
		System.out.println("喝了多少瓶酒："+people.getAlreadyDrinkBeerCount());
		System.out.println("剩余瓶盖数量："+people.getCap());
		System.out.println("剩余酒瓶数量："+people.getBottle());
		System.out.println("当前没喝的酒："+people.getBeer());
	}
	
	/**
	 * 判断一下自己手里的瓶盖或者酒瓶是否能换酒
	 * @return
	 */
	public boolean checkCanExchangeBeers(){
		if(this.getCap() >= Shop.needExchangeBeersByCapNum){
			return true;
		}
		if(this.getBottle() >= Shop.needExchangeBeersByBottleNum){
			return true;
		}
		return false;
	}
	
	public void drinkBeers(){
		//当前未喝的酒
		int beerCount = this.getBeer();
		//开始一瓶瓶的喝酒
		for(int i = 0 ; i < beerCount ; i++){
			this.setBeer(this.getBeer() - 1); //一瓶瓶开始喝
			this.setCap(this.getCap() + 1);	//多了一个瓶盖
			this.setBottle(this.getBottle() + 1);	//多了一个酒瓶
			this.setAlreadyDrinkBeerCount(this.getAlreadyDrinkBeerCount() + 1);	//已经喝酒的数量+1
		}
	}
	
	public void exchangeBeers(){
		//使用酒瓶换的啤酒数量
		Map<String, Integer> exchangeBeersByCap = Shop.exchangeBeersByCap(this.getCap());
		//使用瓶盖换的啤酒数量
		Map<String, Integer> exchangeBeersByBottle = Shop.exchangeBeersByBottle(this.getBottle());
		
		//手里换号的啤酒数量
		this.setBeer(exchangeBeersByCap.get("beer") + exchangeBeersByBottle.get("beer"));
		//换完以后剩余的瓶盖数量
		this.setCap(exchangeBeersByCap.get("cap"));
		//换完以后剩余的酒瓶数量
		this.setBottle(exchangeBeersByBottle.get("bottle"));
	}
	
	/**
	 * 花钱购买啤酒
	 * @param money
	 */
	public void buyBeersFirstByMoney(BigDecimal money){
		if(money != null && money.compareTo(new BigDecimal(0)) > 0){
			int count = money.divide(Shop.beerPrice).intValue();
			this.setBeer(count);
		}
	}
	
	

	public int getAlreadyDrinkBeerCount() {
		return alreadyDrinkBeerCount;
	}

	public void setAlreadyDrinkBeerCount(int alreadyDrinkBeerCount) {
		this.alreadyDrinkBeerCount = alreadyDrinkBeerCount;
	}

	public int getBeer() {
		return beer;
	}

	public void setBeer(int beer) {
		this.beer = beer;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public int getBottle() {
		return bottle;
	}

	public void setBottle(int bottle) {
		this.bottle = bottle;
	}


	/**
	 * 商店 （假设这世界上就只有这一家商店，多家商店还得new）
	 * @author yujianming
	 *
	 */
	public static class Shop{
		
		private static BigDecimal beerPrice = new BigDecimal(2);
		/** 需换一瓶啤酒的瓶盖数量 */
		private static int needExchangeBeersByCapNum = 4;
		/** 需换一瓶啤酒的酒瓶数量 */
		private static int needExchangeBeersByBottleNum = 2;
		
		/**
		 * 商店根据瓶盖数量，返回酒和剩余瓶盖
		 * @param cap
		 * @return
		 */
		public static Map<String, Integer> exchangeBeersByCap(int cap){
			Map<String, Integer> result = new HashMap<String, Integer>();
			result.put("beer", cap / needExchangeBeersByCapNum);
			result.put("cap", cap % needExchangeBeersByCapNum);
			return result;
		}
		/**
		 * 商店根据酒瓶数量，返回酒和剩余酒瓶
		 * @param bottle
		 * @return
		 */
		public static Map<String, Integer> exchangeBeersByBottle(int bottle){
			Map<String, Integer> result = new HashMap<String, Integer>();
			result.put("beer", bottle / needExchangeBeersByBottleNum);
			result.put("bottle", bottle % needExchangeBeersByBottleNum);
			return result;
		}
		
	}
	
}

