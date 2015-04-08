package ibms.ck;

import java.util.ArrayList;
import java.util.List;

import ibms.ck.util.RemoteTool;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SourceDiff extends ActionSupport {

	private boolean success = false;
	private Integer id1;
	private Integer id2;
	private String message = "";
	private boolean warn = false;
	private List<DiffObj> result;
	
	@Override
	public String execute() {
		System.out.println("Diff compare :"+id1+", "+id2);
		if (id1 == null || id1 == 0) {
			message = "The version number can't be empty.";
			warn = true;
			return "success";
		}
		if (id2 == null || id2 == 0) {
			id2 = id1;
			id1 -= 1;
		}
		result = listDiff("svn diff -r "+id1+":"+id2+" http://svn.bioinfo/gpipe/hipipe");
		return "success";
	}

	class DiffObj {
		private String fileName;
		private String para;
		public DiffObj(String fileName, String para) {
			this.fileName = fileName;
			this.para = para;
		}
		public String getFileName() {
			return fileName;
		}
		public String getPara() {
			return para;
		}
	}
	
	private List<DiffObj> listDiff(String str) {
		RemoteTool cmd = new RemoteTool();
		String prevFileName = "";
		String prevPara = "";
		List<DiffObj> result = new ArrayList<>();
		for(String line: cmd.exec(str).split("\n")){
			//System.out.println(">>> "+line);
			if (line.startsWith("Index: ")) {
				if (!prevFileName.equals("")) {
					//System.out.println("File1: "+prevFileName);
					//System.out.println("Para1: "+prevPara);
					result.add(new DiffObj(prevFileName, prevPara));
				}
				prevFileName = line.replace("Index: ", "");
				prevPara = "";
				continue;
			} else if (line.startsWith("+++") || line.startsWith("---") || line.startsWith("===")) {
				continue;
			} else if (line.startsWith("@@")) {
				if (!prevPara.equals("")) {
					//System.out.println("File2: "+prevFileName);
					//System.out.println("Para2: "+prevPara);
					result.add(new DiffObj(prevFileName, prevPara));
				}
				prevPara = "";
			} else {
				if (!line.endsWith("\n")) {
					prevPara += line + "\n";
				} else {
					prevPara += line;
				}
			}
		}
		if (!prevFileName.equals("") && !prevPara.equals("")) {
			//System.out.println("File3: "+prevFileName);
			//System.out.println("Para3: "+prevPara);
			result.add(new DiffObj(prevFileName, prevPara));
		}
		return result;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setId1(String val) {
		if (val == null || val.equals("")) {
			this.id1 = 0;
		} else {
			this.id1 = Integer.valueOf(val);
		}
	}
	
	public void setId2(String val) {
		if (val == null || val.equals("")) {
			this.id2 = 0;
		} else {
			this.id2 = Integer.valueOf(val);
		}
	}

	public List<DiffObj> getResult() {
		return result;
	}

	public Integer getId1() {
		return id1;
	}

	public Integer getId2() {
		return id2;
	}

	public String getMessage() {
		return message;
	}

	public boolean isWarn() {
		return warn;
	}
}
