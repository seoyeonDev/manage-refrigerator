package manager;

public class ManagerPickVo {
	private int seqnum;
	private String name;
	private String imgpath;
	
	public ManagerPickVo() {}
	
	public ManagerPickVo(int seqnum, String name, String imgpath) {
		super();
		this.seqnum = seqnum;
		this.name = name;
		this.imgpath = imgpath;
	}

	public int getSeqnum() {
		return seqnum;
	}

	public void setSeqnum(int seqnum) {
		this.seqnum = seqnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	@Override
	public String toString() {
		return "ManagerPickVo [seqnum=" + seqnum + ", name=" + name + ", imgpath=" + imgpath + "]";
	}
	
	
	
}
