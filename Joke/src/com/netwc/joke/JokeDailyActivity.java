package com.netwc.joke;

import java.util.HashMap;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import android.app.Activity;
import android.os.Bundle;

public class JokeDailyActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daily_list);
	}
	
	public HashMap<String,String> GetDailyJoke() throws ParserException{
		HashMap<String,String> dailyJoke=new HashMap<String,String>();
		final String dailyUrl="http://www.jokeji.cn/";
		Parser htmlParse=new Parser(dailyUrl);
		htmlParse.setEncoding("gb2312");
		NodeList newsContainer=htmlParse.extractAllNodesThatMatch(new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class", "newcontent l_left")));
		if(newsContainer!=null && newsContainer.size()>0){
			
		}
		return dailyJoke;
	}
}
