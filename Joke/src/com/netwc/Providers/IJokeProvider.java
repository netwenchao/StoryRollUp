package com.netwc.Providers;

import java.util.ArrayList;
import java.util.Date;

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

import com.netwc.Entities.CategoryInfo;
import com.netwc.Entities.JokeInfo;

public interface IJokeProvider {
	public ArrayList<CategoryInfo> GetCategorys();	
	public ArrayList<JokeInfo> GetDailyJokes();	
	public ArrayList<JokeInfo> GetCategoryJokes();	
	public JokeInfo GetJokeContent(JokeInfo joke);
}
