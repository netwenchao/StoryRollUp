package com.luckywc.activity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import com.luckywc.controls.RoundCornerListView;
import com.luckywc.medbooks.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class WelcomeActivity extends BaseActivity{

	private ListView lstView;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		lstView=(ListView)findViewById(R.id.lstCategorys);
		lstView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap<String,Object> dataHashMap=(HashMap<String,Object> )arg0.getItemAtPosition(arg2);
				Intent titles=new Intent(WelcomeActivity.this,TitleListActivity.class);				
				titles.putExtra("fileName",dataHashMap.get("file").toString());				
				startActivity(titles);
			}			
		});
		InitAd();
		BindNav();		
	}
	
	private void BindNav(){
		ArrayList<HashMap<String,Object>> navItems=new ArrayList<HashMap<String,Object>>();
		
		HashMap<String,Object> recommend1Item=new HashMap<String,Object>();
		recommend1Item.put("resId",R.drawable.recommend1);
		recommend1Item.put("title",getString(R.string.TextView1));
		recommend1Item.put("file","recommend1.xml");
		navItems.add(recommend1Item);

		HashMap<String,Object> recommend2Item=new HashMap<String,Object>();
		recommend2Item.put("resId",R.drawable.recommend2);
		recommend2Item.put("title",getString(R.string.TextView2));
		recommend2Item.put("file","recommend2.xml");
		navItems.add(recommend2Item);

		HashMap<String,Object> recommend3Item=new HashMap<String,Object>();
		recommend3Item.put("resId",R.drawable.recommend3);
		recommend3Item.put("title",getString(R.string.TextView3));
		recommend3Item.put("file","recommend3.xml");
		navItems.add(recommend3Item);

		HashMap<String,Object> recommend4Item=new HashMap<String,Object>();
		recommend4Item.put("resId",R.drawable.recommend4);
		recommend4Item.put("title",getString(R.string.TextView4));
		recommend4Item.put("file","recommend4.xml");
		navItems.add(recommend4Item);

		HashMap<String,Object> searchItem=new HashMap<String,Object>();
		searchItem.put("resId",R.drawable.search);
		searchItem.put("title",getString(R.string.TextView5));
		searchItem.put("file","children.xml");//儿科
		navItems.add(searchItem);

		HashMap<String,Object> userItem=new HashMap<String,Object>();
		userItem.put("resId",R.drawable.user);
		userItem.put("title",getString(R.string.TextView6));
		userItem.put("file","interna.xml");//内科
		navItems.add(userItem);

		HashMap<String,Object> settingsItem=new HashMap<String,Object>();
		settingsItem.put("resId",R.drawable.settings);
		settingsItem.put("title",getString(R.string.TextView7));
		settingsItem.put("file","surgical.xml");//外壳
		navItems.add(settingsItem);

		HashMap<String,Object> wuguanItem=new HashMap<String,Object>();
		wuguanItem.put("resId",R.drawable.wuguan);
		wuguanItem.put("title",getString(R.string.TextView8));
		wuguanItem.put("file","face.xml");//五官
		navItems.add(wuguanItem);

		HashMap<String,Object> trainItem=new HashMap<String,Object>();
		trainItem.put("resId",R.drawable.train);
		trainItem.put("title",getString(R.string.TextView9));
		trainItem.put("file","skin.xml");//皮肤科
		navItems.add(trainItem);

		HashMap<String,Object> maleItem=new HashMap<String,Object>();
		maleItem.put("resId",R.drawable.male);
		maleItem.put("title",getString(R.string.TextView10));
		maleItem.put("file","male.xml");
		navItems.add(maleItem);

		HashMap<String,Object> femaleItem=new HashMap<String,Object>();
		femaleItem.put("resId",R.drawable.female);
		femaleItem.put("title",getString(R.string.TextView11));
		femaleItem.put("file","female.xml");
		navItems.add(femaleItem);

		HashMap<String,Object> markerItem=new HashMap<String,Object>();
		markerItem.put("resId",R.drawable.marker);
		markerItem.put("title",getString(R.string.TextView12));
		markerItem.put("file","beautify.xml");//美容
		navItems.add(markerItem);

		HashMap<String,Object> linbaItem=new HashMap<String,Object>();
		linbaItem.put("resId",R.drawable.linba);
		linbaItem.put("title",getString(R.string.TextView13));
		linbaItem.put("file","jian.xml");//淋巴
		navItems.add(linbaItem);

		HashMap<String,Object> parkItem=new HashMap<String,Object>();
		parkItem.put("resId",R.drawable.park);
		parkItem.put("title",getString(R.string.TextView14));
		parkItem.put("file","tumour.xml");//肿瘤
		navItems.add(parkItem);

		HashMap<String,Object> convenienceItem=new HashMap<String,Object>();
		convenienceItem.put("resId",R.drawable.convenience);
		convenienceItem.put("title",getString(R.string.TextView15));
		convenienceItem.put("file","subject.xml");//
		navItems.add(convenienceItem);

		HashMap<String,Object> toilets1Item=new HashMap<String,Object>();
		toilets1Item.put("resId",R.drawable.toilets1);//dailylife.xml
		toilets1Item.put("title",getString(R.string.TextView16));
		toilets1Item.put("file","dailylife.xml");
		navItems.add(toilets1Item);

		HashMap<String,Object> rufangItem=new HashMap<String,Object>();
		rufangItem.put("resId",R.drawable.rufang);
		rufangItem.put("title",getString(R.string.TextView17));
		rufangItem.put("file","rufang.xml");
		navItems.add(rufangItem);

		HashMap<String,Object> jijiuItem=new HashMap<String,Object>();
		jijiuItem.put("resId",R.drawable.jijiu);//youmi.xml
		jijiuItem.put("title",getString(R.string.TextView18));
		jijiuItem.put("file","youmi.xml");
		navItems.add(jijiuItem);

		HashMap<String,Object> yankeItem=new HashMap<String,Object>();
		yankeItem.put("resId",R.drawable.yanke);
		yankeItem.put("title",getString(R.string.TextView19));
		yankeItem.put("file","yanke.xml");
		navItems.add(yankeItem);

		HashMap<String,Object> yunfuItem=new HashMap<String,Object>();
		yunfuItem.put("resId",R.drawable.yunfu);
		yunfuItem.put("title",getString(R.string.TextView20));
		yunfuItem.put("file","yunfu.xml");
		navItems.add(yunfuItem);

		HashMap<String,Object> museumItem=new HashMap<String,Object>();
		museumItem.put("resId",R.drawable.museum);
		museumItem.put("title",getString(R.string.TextView21));
		museumItem.put("file","hidea.xml");
		navItems.add(museumItem);

		HashMap<String,Object> hide2Item=new HashMap<String,Object>();
		hide2Item.put("resId",R.drawable.hide2);
		hide2Item.put("title",getString(R.string.TextView22));
		hide2Item.put("file","hideb.xml");
		navItems.add(hide2Item);

		HashMap<String,Object> hide3Item=new HashMap<String,Object>();
		hide3Item.put("resId",R.drawable.hide3);
		hide3Item.put("title",getString(R.string.TextView23));
		hide3Item.put("file","hidec.xml");
		navItems.add(hide3Item);

		HashMap<String,Object> hide4Item=new HashMap<String,Object>();
		hide4Item.put("resId",R.drawable.hide4);
		hide4Item.put("title",getString(R.string.TextView24));
		hide4Item.put("file","hided.xml");
		navItems.add(hide4Item);

		SimpleAdapter adapter=new SimpleAdapter(this,navItems,R.layout.item_nav,new String []{"resId","title"},new int[]{R.id.imgIcon,R.id.tvTitle});
		lstView.setAdapter(adapter);

	}
}
