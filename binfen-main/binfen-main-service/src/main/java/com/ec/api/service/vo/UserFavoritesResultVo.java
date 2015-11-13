package com.ec.api.service.vo;

/**
 * User: iboy
 * Date: 2014-9-28
 * Time: 4:51:50
 */
public class UserFavoritesResultVo {
    /** 用户ID */
    private Integer userId;
    /** 商品ID */
    private Integer itemId;
    /** 是否关注  */
    private Boolean favorite;

    public UserFavoritesResultVo() {}

    public UserFavoritesResultVo(Integer userId,Integer itemId,Boolean favorite){
        this.userId = userId;
        this.itemId = itemId;
        this.favorite = favorite;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "UserFavoritesResultVo{" +
                "userId=" + userId +
                ", itemId=" + itemId +
                ", favorite=" + favorite +
                '}';
    }
}