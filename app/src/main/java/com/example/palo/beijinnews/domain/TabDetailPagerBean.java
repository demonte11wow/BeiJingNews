package com.example.palo.beijinnews.domain;

import java.util.List;

/**
 * 作者：尚硅谷-杨光福 on 2016/10/18 11:44
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：页签页面的Bean
 */
public class TabDetailPagerBean {


    /**
     * countcommenturl : /client/content/countComment/
     * more : /static/api/news/10007/list_2.json
     * news : [{"id":147314,"title":"徒步登高欣赏京城第一片红叶","url":"/static/html/2015/10/20/714C6E50486D197B6F257C45.html","listimage":"/static/images/2015/10/20/3/1420927604KB07.jpg","pubdate":"2015-10-20 09:37","comment":true,"commenturl":"/client/user/newComment/147314","type":"news","commentlist":"/static/api/news/10012/14/147314/comment_1.json"}]
     * title : 北京
     * topic : []
     * topnews : [{"comment":true,"commentlist":"/static/api/news/10007/53/147253/comment_1.json","commenturl":"/client/user/newComment/147253","id":147253,"pubdate":"2015-10-19 07:18","title":"市教委：中高考英语试卷结构不变","topimage":"/static/images/2015/10/19/36/1053274969EORV.jpg","type":"news","url":"/static/html/2015/10/19/714C6E504A6F1B7869277C42.html"}]
     */

    private DataEntity data;
    /**
     * data : {"countcommenturl":"/client/content/countComment/","more":"/static/api/news/10007/list_2.json","news":[{"id":147314,"title":"徒步登高欣赏京城第一片红叶","url":"/static/html/2015/10/20/714C6E50486D197B6F257C45.html","listimage":"/static/images/2015/10/20/3/1420927604KB07.jpg","pubdate":"2015-10-20 09:37","comment":true,"commenturl":"/client/user/newComment/147314","type":"news","commentlist":"/static/api/news/10012/14/147314/comment_1.json"}],"title":"北京","topic":[],"topnews":[{"comment":true,"commentlist":"/static/api/news/10007/53/147253/comment_1.json","commenturl":"/client/user/newComment/147253","id":147253,"pubdate":"2015-10-19 07:18","title":"市教委：中高考英语试卷结构不变","topimage":"/static/images/2015/10/19/36/1053274969EORV.jpg","type":"news","url":"/static/html/2015/10/19/714C6E504A6F1B7869277C42.html"}]}
     * retcode : 200
     */

    private int retcode;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public DataEntity getData() {
        return data;
    }

    public int getRetcode() {
        return retcode;
    }

    public static class DataEntity {
        private String countcommenturl;
        private String more;
        private String title;
        /**
         * id : 147314
         * title : 徒步登高欣赏京城第一片红叶
         * url : /static/html/2015/10/20/714C6E50486D197B6F257C45.html
         * listimage : /static/images/2015/10/20/3/1420927604KB07.jpg
         * pubdate : 2015-10-20 09:37
         * comment : true
         * commenturl : /client/user/newComment/147314
         * type : news
         * commentlist : /static/api/news/10012/14/147314/comment_1.json
         */

        private List<NewsEntity> news;
        private List<?> topic;
        /**
         * comment : true
         * commentlist : /static/api/news/10007/53/147253/comment_1.json
         * commenturl : /client/user/newComment/147253
         * id : 147253
         * pubdate : 2015-10-19 07:18
         * title : 市教委：中高考英语试卷结构不变
         * topimage : /static/images/2015/10/19/36/1053274969EORV.jpg
         * type : news
         * url : /static/html/2015/10/19/714C6E504A6F1B7869277C42.html
         */

        private List<TopnewsEntity> topnews;

        public void setCountcommenturl(String countcommenturl) {
            this.countcommenturl = countcommenturl;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setNews(List<NewsEntity> news) {
            this.news = news;
        }

        public void setTopic(List<?> topic) {
            this.topic = topic;
        }

        public void setTopnews(List<TopnewsEntity> topnews) {
            this.topnews = topnews;
        }

        public String getCountcommenturl() {
            return countcommenturl;
        }

        public String getMore() {
            return more;
        }

        public String getTitle() {
            return title;
        }

        public List<NewsEntity> getNews() {
            return news;
        }

        public List<?> getTopic() {
            return topic;
        }

        public List<TopnewsEntity> getTopnews() {
            return topnews;
        }

        public static class NewsEntity {
            private int id;
            private String title;
            private String url;
            private String listimage;
            private String pubdate;
            private boolean comment;
            private String commenturl;
            private String type;
            private String commentlist;

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getUrl() {
                return url;
            }

            public String getListimage() {
                return listimage;
            }

            public String getPubdate() {
                return pubdate;
            }

            public boolean isComment() {
                return comment;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public String getType() {
                return type;
            }

            public String getCommentlist() {
                return commentlist;
            }
        }

        public static class TopnewsEntity {
            private boolean comment;
            private String commentlist;
            private String commenturl;
            private int id;
            private String pubdate;
            private String title;
            private String topimage;
            private String type;
            private String url;

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setTopimage(String topimage) {
                this.topimage = topimage;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isComment() {
                return comment;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public int getId() {
                return id;
            }

            public String getPubdate() {
                return pubdate;
            }

            public String getTitle() {
                return title;
            }

            public String getTopimage() {
                return topimage;
            }

            public String getType() {
                return type;
            }

            public String getUrl() {
                return url;
            }
        }
    }
}
