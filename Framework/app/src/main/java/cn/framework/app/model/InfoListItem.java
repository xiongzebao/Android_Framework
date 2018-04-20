package cn.framework.app.model;

import java.io.Serializable;

public class InfoListItem   implements Serializable{

    public static final int INFO_TYPE_TEXT = 1;
    public static final int INFO_TYPE_VIDEO = 2;
    public static final int INFO_TYPE_IMG = 3;

	private Long infoID;
	private String title;
	private String subTitle;
	private String labels;// 标签属性 存储格式-- ID:名称;ID:名称
	private String pictureURL;// 图片地址
	private String content;//内容
	private Integer commentFlag;// 允许评论标记 1-允许评论 2-不允许评论
	private Integer layout;// 内容布局 0-代表空内容提示,1-图文混排 2-视频 3-图片
    private String publishDateStr;//资讯发布时间
	private String publishDate;
	private String createdOnStr;
	private String createdOn;
	private String author;// 作者
	private String shareURL;// 分享地址
	private Boolean isDel = false;//该资讯是否已被删除
	private Integer interestFlag=5;//感兴趣状态  1.感兴趣  2.不感兴趣  5.感兴趣不感兴趣都没点击

    //private InfoContentDO contentDO;//资讯详情H5内容

	public Integer getInterestFlag() {return interestFlag;}
	public void setInterestFlag(Integer interestFlag){this.interestFlag = interestFlag;}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	public Long getInfoID() {
		return infoID;
	}
	public void setInfoID(Long infoID) {
		this.infoID = infoID;
	}
	public Integer getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(Integer commentFlag) {
		this.commentFlag = commentFlag;
	}
	public Integer getLayout() {
		if (layout==null){
			return 1;
		}
		return layout;
	}
	public void setLayout(Integer layout) {
		this.layout = layout;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

   /* public InfoContentDO getContentDO() {
        return contentDO;
    }
    public void setContentDO(InfoContentDO contentDO) {
        this.contentDO = contentDO;
    }
	public String getHtmlContent(){
		if (contentDO==null){
			return null;
		}
		return contentDO.getContent();
	}*/
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
	public String getShareURL() {
		return shareURL;
	}
	public void setShareURL(String shareURL) {
		this.shareURL = shareURL;
	}

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }

	public String getPublishDateStr() {return publishDateStr;}

	public void setPublishDateStr(String publishDateStr) {this.publishDateStr = publishDateStr;}

	public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getCreatedOnStr() {
		return createdOnStr;
	}

	public void setCreatedOnStr(String createdOnStr) {
		this.createdOnStr = createdOnStr;
	}

}
