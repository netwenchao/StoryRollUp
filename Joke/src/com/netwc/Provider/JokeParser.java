package com.netwc.Provider;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import android.content.SharedPreferences.Editor;
import android.util.Log;

public class JokeParser {
	final String dailyUrl="http://www.jokeji.cn";	

	public void GetPageIndexData(){
		ArrayList<com.netwc.Provider.Entities.JokeInfo> jokes=new ArrayList<com.netwc.Provider.Entities.JokeInfo>();
		Parser htmlParse;
		try {
			htmlParse = new Parser(dailyUrl);
			htmlParse.setEncoding("gb2312");
	
			NodeList newsContainer=htmlParse.extractAllNodesThatMatch(new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class", "newcontent l_left")));
			if(newsContainer!=null && newsContainer.size()>0){
				NodeList lis=newsContainer.elementAt(0).getChildren().extractAllNodesThatMatch(new TagNameFilter("ul"));
				if(lis!=null && lis.size()>0){
					lis=lis.elementAt(0).getChildren().extractAllNodesThatMatch(new TagNameFilter("li"));
				}
				if(lis!=null && lis.size()>0){
					NodeList liSubNodes;
					NodeList aTags;
					NodeList spanTags;
					Node liNode;
					for(int i=0;i<lis.size();i++){
						liNode=lis.elementAt(i);
						liSubNodes=liNode.getChildren();
						aTags=liSubNodes.extractAllNodesThatMatch(new AndFilter(new TagNameFilter("a"),new HasAttributeFilter("target", "_blank")));
						spanTags=liSubNodes.extractAllNodesThatMatch(new TagNameFilter("span"));
						if(aTags!=null && aTags.size()>0){
							com.netwc.Provider.Entities.JokeInfo joke=new com.netwc.Provider.Entities.JokeInfo();
							joke.Title=((LinkTag)aTags.elementAt(0)).getLinkText();
							String url;
							url = dailyUrl+((TagNode)aTags.elementAt(0)).getAttribute("href");
							joke.Url=UrlEncodeChina(url);								
							if(spanTags!=null && spanTags.size()>0){
								joke.SiteDate=((Span)spanTags.elementAt(0)).getStringText();	
							}else{
								joke.SiteDate="";
							}
							jokes.add(joke);
						}							
					}
				}
				for(int i=0;i<jokes.size();i++){
					com.netwc.Provider.Entities.JokeInfo joke=jokes.get(i);
					String url=joke.Url;
					if(url!=null && url.length()>0){							
						String content=GetContent(url);
						joke.Content=content;
					}
					Log.v("Data",joke.Title);						
					joke.dateAdd=(new Date()).getTime();
					//db.AddJoke(joke);
				}
			}		
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		/*
		Editor edit=sp.edit();
		Date now=new Date();
		edit.putLong(spName, now.getTime());
		edit.commit();
		*/
		//db.Close();
	}

	private String GetContent(String url){
		try {
			Parser htmlParse = new Parser(url);
			htmlParse.setEncoding("gb2312");
			NodeList jkContent=htmlParse.extractAllNodesThatMatch(new AndFilter(new TagNameFilter("span"),new HasAttributeFilter("id", "text110")));
			if(jkContent!=null && jkContent.size()>0){
				return RemoveHtmlCode(jkContent.toHtml());
			}
			Log.v("Content", jkContent.toHtml());
			return "";
		}
		catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("ErrorMsg",e.getMessage());
			return "";
		}
	}
	
	private String UrlEncodeChina(String str){
		Pattern p=Pattern.compile("(?m)(?u)([\u4E00-\u9FA5]+)");
		String outStr=str;
		Matcher m=p.matcher(str);
		while(m.find()){
			Log.v("Match",Integer.toString(m.group(1).length()));
			Log.v("Match",m.group(1));
			outStr=outStr.replace(m.group(1),URLEncoder.encode(m.group(1)));
		}
		return outStr;
	}
	
	private String RemoveHtmlCode(String str){
		return str.replaceAll("(?m)(?u)\\<br[ ]*(/)*\\>","\n").replaceAll("(?m)\\<[^<>]*\\>","")
		.replaceAll("(?m)(?u)&amp;","&")
		.replaceAll("(?m)(?u)&lt;","<")
		.replaceAll("(?m)(?u)&gt;",">")
		.replaceAll("(?m)(?u)&quot;","\"")
		.replaceAll("(?m)(?u)&apos;","'")
		.replaceAll("(?m)(?u)&nbsp[ ]*;"," ");
	}
	
	public void DownLoadCategorys(){
		
	}
}
