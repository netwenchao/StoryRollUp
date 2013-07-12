package com.netwc.Provider;
import java.util.ArrayList;
import java.util.HashMap;

import com.netwc.PublicFunc.EnumMgr.DataFrom;

public class Entities {
	public static class JokeInfo{
		public Integer ID;
		public String Title;
		public String Content;
		public String SiteDate;
		public String Url;
		public long dateAdd;
		public Boolean IsFavourite;		
		public DataFrom DataFrom;
		public Boolean IsView;
	}

	public static class CategoryInfo{
		public Integer ID;
		public String categoryname;
		public DataFrom DataFrom;
	}
	
	public static class LinkNodeData{
		public LinkNodeData(String url,String text){			
			this.Url=url;
			this.Text=text;
		}
		public String Url;
		public String Text;
	}
	
	public static class NodeData{
		public NodeData(){			
		}	
		public NodeData(String content,ArrayList<HashMap<String,String>> hashMap){
			Content=content;
			Attributes=hashMap;
		}		
		public String Content;
		public ArrayList<HashMap<String,String>> Attributes;
	}
}
