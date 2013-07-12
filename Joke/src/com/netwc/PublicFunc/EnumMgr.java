package com.netwc.PublicFunc;

public class EnumMgr {
	public static enum DataFrom
	{
		JOKEJI("JOKEJI",0),QIUSHI("QIUSHI",1),LOCAL("LOCAL",2);
		int value;
		String name;

		private DataFrom(String eName,int arg3)
		{
		  this.name=eName;
		  this.value=arg3;
		}

		public static DataFrom getDataFromByValue(int paramInt)
		{
			switch (paramInt)
			{				
				case 0:
					return JOKEJI;
				case 1:
					return QIUSHI;
				case 2:
					return LOCAL;
				default:
					return LOCAL;
			}			
		}

		public int getValue()
		{
		  return this.value;
		}
	}
}
