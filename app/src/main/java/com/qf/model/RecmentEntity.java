package com.qf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 推荐页(首页)数据实体类
 */
public class RecmentEntity{

    private List<TabTitle> san_can_titles;
    private List<SanCan> san_can;
    private List<Top2> top2;
    private Top4 top4;
    private List<Shop> shops;

    /**
     * 首页顶部时间段tab -- 早餐 午餐等等 那个东西
     */
    public class TabTitle{
        private String title;
        private String titlepic;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitlepic() {
            return titlepic;
        }

        public void setTitlepic(String titlepic) {
            this.titlepic = titlepic;
        }
    }

    /**
     * 首页菜单缩略图
     */
    public class SanCan implements Serializable{
        private String id;
        private String titlepic;//菜单大图
        private String titlepic_m;//菜单缩略图
        private String title;//标题
        private String descr;//简介

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitlepic() {
            return titlepic;
        }

        public void setTitlepic(String titlepic) {
            this.titlepic = titlepic;
        }

        public String getTitlepic_m() {
            return titlepic_m;
        }

        public void setTitlepic_m(String titlepic_m) {
            this.titlepic_m = titlepic_m;
        }
    }

    /**
     * 首页 精选活动 推荐食材等viewpager数据
     */
    public class Top2 implements Serializable{
        private String title;
        private int type;
        private ArrayList<TopObj> obj;

        public class TopObj implements Serializable{
            //精选活动
            private String title;
            private String descr;
            private String photo;
            private int click_type;
            private String click_obj;

            //今日推荐
            private String id;
            private String image;
            private int heat_level;
            private String heat_word;
            private String gongxiao;

            //今日推荐达人
            private String user_id;
            private String user_name;
            private String avatar;
            private String level;

            //今日推荐菜单
            private List<String> recipes;

            public String getClick_obj() {
                return click_obj;
            }

            public void setClick_obj(String click_obj) {
                this.click_obj = click_obj;
            }

            public int getClick_type() {
                return click_type;
            }

            public void setClick_type(int click_type) {
                this.click_type = click_type;
            }

            public String getDescr() {
                return descr;
            }

            public void setDescr(String descr) {
                this.descr = descr;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getGongxiao() {
                return gongxiao;
            }

            public void setGongxiao(String gongxiao) {
                this.gongxiao = gongxiao;
            }

            public int getHeat_level() {
                return heat_level;
            }

            public void setHeat_level(int heat_level) {
                this.heat_level = heat_level;
            }

            public String getHeat_word() {
                return heat_word;
            }

            public void setHeat_word(String heat_word) {
                this.heat_word = heat_word;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public List<String> getRecipes() {
                return recipes;
            }

            public void setRecipes(List<String> recipes) {
                this.recipes = recipes;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }

        public void setObj(ArrayList<TopObj> obj) {
            this.obj = obj;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public ArrayList<TopObj> getObj() {
            return obj;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }
    }


    /**
     * 首页 广告位 数据实体
     */
    public class Top4 implements Serializable{
        private String photo;
        private int click_type;
        private String click_obj;

        public String getClick_obj() {
            return click_obj;
        }

        public void setClick_obj(String click_obj) {
            this.click_obj = click_obj;
        }

        public int getClick_type() {
            return click_type;
        }

        public void setClick_type(int click_type) {
            this.click_type = click_type;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }

    /**
     * 首页 正在抢购模块 数据实体
     */
    public class Shop implements Serializable {
        private String category;
        private String id;
        private String title;
        private String desc;
        private String image;
        private long start_time;
        private long left_time;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public long getLeft_time() {
            return left_time;
        }

        public void setLeft_time(long left_time) {
            this.left_time = left_time;
        }

        public long getStart_time() {
            return start_time;
        }

        public void setStart_time(long start_time) {
            this.start_time = start_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    /**
     * set 和 get方法
     * @return
     */
    public List<SanCan> getSan_can() {
        return san_can;
    }

    public void setSan_can(List<SanCan> san_can) {
        this.san_can = san_can;
    }

    public List<TabTitle> getSan_can_titles() {
        return san_can_titles;
    }

    public void setSan_can_titles(List<TabTitle> san_can_titles) {
        this.san_can_titles = san_can_titles;
    }

    public List<Top2> getTop2() {
        return top2;
    }

    public void setTop2(List<Top2> top2) {
        this.top2 = top2;
    }

    public Top4 getTop4() {
        return top4;
    }

    public void setTop4(Top4 top4) {
        this.top4 = top4;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
