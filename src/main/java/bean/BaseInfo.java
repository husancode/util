package bean;

import java.util.Date;

/**
 * @Auther: husan
 * @Date: 2019/12/13 11:30
 * @Description:
 */
public class BaseInfo {

	private String id;

	private Date createDate;

	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
