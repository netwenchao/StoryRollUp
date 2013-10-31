package com.netwc.DataProvider;

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

import com.netwc.Entities.CategoryInfo;
import com.netwc.Entities.JokeInfo;

import android.util.Log;

public class JokeJi_Provider{// extends AbsJokeProvider implements IJokeProvider{
	/*
	public String PageUrl = "http://www.jokeji.cn/";
    public String HostAddress = "http://www.jokeji.cn/";
    private String PageCoding="gb2312";	
	
	@Override
	public ArrayList<CategoryInfo> GetCategorys() {
		
        <div class="joketype l_left">
           <ul>
               <li> <a href="/list29_1.htm">��Ц��Ů(945)</a></li>
               <li> <a href="/list13_1.htm">���(722)</a></li>
               <li> <a href="/list43_1.htm">��Ц��(656)</a></li>
               <li> <a href="/list5_1.htm">У԰(537)</a></li>
               <li> <a href="/list27_1.htm">�ۺ�(445)</a></li>
               <li> <a href="/list7_1.htm">��ͯ(400)</a></li>
               <li> <a href="/list1_1.htm">����(372)</a></li>
               <li> <a href="/list12_1.htm">����(296)</a></li>
               <li> <a href="/list4_1.htm">��ͥ(265)</a></li>
               <li> <a href="/list16_1.htm">ְ��(254)</a></li>
               <li> <a href="/list40_1.htm">��Цǩ��(250)</a></li>
               <li> <a href="/list36_1.htm">����Ц��(245)</a></li>
               <li> <a href="/list39_1.htm">��Ĭ����(187)</a></li>
           </ul>
       </div>

		ArrayList<CategoryInfo> jokes=new ArrayList<CategoryInfo>();
		Parser htmlParse;
		try {
			htmlParse = new Parser(PageUrl);
			htmlParse.setEncoding(PageCoding);
			
			NodeList categoryContainer=htmlParse.extractAllNodesThatMatch(new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class", "joketype l_left")));
			if(categoryContainer!=null && categoryContainer.size()>0){
				NodeList lis=categoryContainer.elementAt(0).getChildren().extractAllNodesThatMatch(new TagNameFilter("li"));
				if(lis!=null && lis.size()>0){
					Node liNode;
					NodeList liSubNode;
					for(int i=0;i<lis.size();i++){
						liNode=lis.elementAt(i);
						liSubNode=liNode.getChildren().extractAllNodesThatMatch(new TagNameFilter("a"));
						if(liSubNode!=null && liSubNode.size()>0){
							CategoryInfo catInfo=new CategoryInfo();
							String catName=((LinkTag)liSubNode.elementAt(0)).getLink();
							catInfo.Name=catName.indexOf("(")>0?catName.substring(0,catName.indexOf("(")):catName;
							String url= HostAddress+((TagNode)liSubNode.elementAt(0)).getAttribute("href");
							catInfo.PageUrl=UrlEncodeChina(url);
						}
					}
				}
			}
		}catch(ParserException e){
			
		}
		return null;
	}

	/*
    <div class="newcontent l_left">
        <ul>
            <li><img src="/images/d02.gif" width="8" height="10" border="0"><a href="/jokehtml/fq/20130820000335.htm" target="_blank">�����������̵ĳ�����</a>(5974)<span>2013-8-20</span><span><img src="/images/new.gif" width="28" height="11" border="0"></span></li>
            <li><img src="/images/d02.gif" width="8" height="10" border="0"><a href="/jokehtml/ert/20130820000245.htm" target="_blank">�����ְ������ܺ���</a>(4410)<span>2013-8-20</span><span><img src="/images/new.gif" width="28" height="11" border="0"></span></li>
            <li><img src="/images/d02.gif" width="8" height="10" border="0"><a href="/jokehtml/gj/20130820000225.htm" target="_blank">��������������˹�ֵ�����Ĭ</a>(2707)<span>2013-8-20</span><span><img src="/images/new.gif" width="28" height="11" border="0"></span></li>
            <li><img src="/images/d02.gif" width="8" height="10" border="0"><a href="/jokehtml/bxqm/20130820000125.htm" target="_blank">�Ҳ��Բ�,ֻ���뷨���ض���</a>(3097)<span>2013-8-20</span><span><img src="/images/new.gif" width="28" height="11" border="0"></span></li>
            <li><img src="/images/d02.gif" width="8" height="10" border="0"><a href="/jokehtml/xy/20130819000425.htm" target="_blank">Ц�����У԰����</a>(25373)<span>2013-8-19</span><span><img src="/images/new.gif" width="28" height="11" border="0"></span></li>
            <li><img src="/images/d02.gif" width="8" height="10" border="0"><a href="/jokehtml/bxnn/20130819000241.htm" target="_blank">�ʰ��·����������</a>(17797)<span>2013-8-19</span><span><img src="/images/new.gif" width="28" height="11" border="0"></span></li>
            <li><img src="/images/d02.gif" width="8" height="10" border="0"><a href="/jokehtml/bxqm/20130819000201.htm" target="_blank">���և��������</a>(15385)<span>2013-8-19</span><span><img src="/images/new.gif" width="28" height="11" border="0"></span></li>
            <li><img src="/images/d02.gif" width="8" height="10" border="0"><a href="/jokehtml/jsxh/20130819000057.htm" target="_blank">����ľ������Ц</a>(9841)<span>2013-8-19</span><span><img src="/images/new.gif" width="28" height="11" border="0"></span></li>
            <li><img src="/images/d02.gif" width="8" height="10" border="0"><a href="/jokehtml/��Ц��/20130818000359.htm" target="_blank">0��Ц��,�մӱ������ó���</a>(33788)<span>2013-8-18</span><span><img src="/images/new.gif" width="28" height="11" border="0"></span></li>
            <li><img src="/images/d02.gif" width="8" height="10" border="0"><a href="/jokehtml/bxnn/20130818000240.htm" target="_blank">û���ȶȵ����������</a>(21237)<span>2013-8-18</span><span><img src="/images/new.gif" width="28" height="11" border="0"></span></li>
        </ul>
    </div> 

	@Override
	public ArrayList<JokeInfo> GetDailyJokes() {		
		ArrayList<JokeInfo> jokes=new ArrayList<JokeInfo>();
		Parser htmlParse;
		try {
			htmlParse = new Parser(PageUrl);
			htmlParse.setEncoding(PageCoding);
	
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
							JokeInfo joke=new JokeInfo();
							joke.Title=((LinkTag)aTags.elementAt(0)).getLinkText();
							String url;
							url = HostAddress+((TagNode)aTags.elementAt(0)).getAttribute("href");
							joke.Url=UrlEncodeChina(url);								
							if(spanTags!=null && spanTags.size()>0){
								joke.SiteDate=((Span)spanTags.elementAt(0)).getStringText();	
							}else{
								joke.SiteDate="";
							}
							joke.DateAdd=(new Date()).getTime();
							joke.IsDownLoad=false;
							joke.IsNew=true;
							joke.IsFavourite=false;
							if(joke.Url!="") jokes.add(joke);
						}							
					}
				}				
			}		
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return jokes;
	}

	@Override
	public ArrayList<JokeInfo> GetCategoryJokes() {
		//Page Regex list29_7.htm
        /*
        <div class="list_title">
        <ul>
            <li><b><a href="/jokehtml/bxnn/20130819000241.htm" target="_blank">�ʰ��·����������</a></b><span>�����69��</span><i>2013-8-19</i></li>
            <li><b><a href="/jokehtml/bxnn/20130818000240.htm" target="_blank">û���ȶȵ����������</a></b><span>�����11190��</span><i>2013-8-18</i></li>
            <li><b><a href="/jokehtml/bxnn/20130813000313.htm" target="_blank">����,��Խ��Խ����Լ���</a></b><span>�����44470��</span><i>2013-8-13</i></li>
        </ul>
        </div>

		return null;
	}

	@Override
	public JokeInfo GetJokeContent(JokeInfo joke) {
		/*
        <div class="mob_txt">
            <div class="ad360_280">
            <script type="text/javascript">BAIDU_CLB_fillSlot("31558");</script><!--336-280-->
            </div>
            <span><b>�Ķ�����</b>������ ���� �ҡ� ��ҳ��Ctrl+D �ղر�ƪЦ��</span><br>
            <span id="text110"><p>1���������ʡ����������𣿡�<br>�Ҿ������»ش�������û�С�<br>��Ц��˵��̫���ˣ��� <br>�ѵ��Ҿ�Υ�İ���Ҫ���ˣ�����ֻ������������˵�������Ҹջ����룬��������������Ķ���Ů�����������Ҫ�����˰����Һ���û�С���</p>
            <p>2���������������������˵�е�Ů���ӣ�һ��Ե�ţ�ţ���˵�ò��ߵ��棬һֱ�߷�߶߶�����鷳��<br>����ʵ���̲�ס�˰���������һ�䣺������Ա����˫���ӣ���<br>�ڷ���Ա���ͳ�ţ�Ų����ÿ��Ӻ����ִ������˾䣺��һ��������Ҳ�У���</p>
            <p>3����������Ů������·�ϣ�������ǰŮ�ѣ������ΰ���<br>�Ҽ�æ��ǰŮ�ѽ���˵����������ɩ�ӡ���<br>����Ů��˵������λ�ǣ���<br>ǰŮ�Ѹϻ�˵��������ǰ����ɩ�ӣ���</p>
            <p>4����ͬ�����죬һͬ��˵������Ҫ��Ҫ������Ů���ѣ��Ұ���3�ױ�������������ۡ���<br>һͬ��˵����ǧ����˵����˵�����ͳ�������ˣ���</p></span><br>

            <font color="#009900">��Ц�����ϣ�Ц���� www.jokeji.cn</font> 
            <br>
            <br>
            <div class="clear"></div>


            <div class="pl_ad">
            <ul>
            <li><b><a href="#bbs">��������</a></b></li>
            <li><span id="topelite" onclick="javascript:check('../../inc/topelite.asp?nid=8070','topelite')" alt="�Ƽ�">Ʊ��:20</span></li>
            </ul>

            <div class="clear"></div>

            <div class="ad468_date">
            <b><script type="text/javascript">BAIDU_CLB_fillSlot("133131");</script> <!-- GG-2 --></b>
            <i>����ʱ�䣺2013-8-19 0:03:00</i>
            </div>
            </div>
            <div class="clear"></div>
        </div> 
      
		try {
			Parser htmlParse = new Parser(joke.Url);
			htmlParse.setEncoding("gb2312");
			NodeList jkContent=htmlParse.extractAllNodesThatMatch(new AndFilter(new TagNameFilter("span"),new HasAttributeFilter("id", "text110")));
			if(jkContent!=null && jkContent.size()>0){
				joke.Content=RemoveHtmlCode(jkContent.toHtml());
			}			
			joke.Content="";
			joke.IsDownLoad=true;
			joke.IsNew=true;
		}
		catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("ErrorMsg",e.getMessage());	
			joke.IsDownLoad=false;
		}
		return joke;
	}
  */
}
