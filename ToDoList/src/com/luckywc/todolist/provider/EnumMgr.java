package com.luckywc.todolist.provider;

public class EnumMgr {
	public enum TaskPriority{
		High("High",0),Normal("Normal",1),Low("Low",2);
		private int value;
		private String name;
		private TaskPriority(String eName,int arg3)
		{
		  this.name=eName;
		  this.value =arg3;
		}

		public static TaskPriority getTaskPriorityByValue(int paramInt)
		{
			switch (paramInt)
			{				
				case 0:
					return High;
				case 1:
					return Normal;
				case 2:
					return Low;
				default:
					return null;
			}
		}

		public int getValue(){
		  return this.value;
		}
		
		public String getName() {
			return this.name;
		}
	}

	public enum TaskStatus{
		NotStart("NotStart",0),
		InProcess("InProcess",1),
		Completed("Completed",2),
		Deffered("Deffered",3);
		
		private String name;
		private int value;
		
		private TaskStatus(String eName,int idx){
			name=eName;
			value=idx;
		}
		
		public static TaskStatus getTaskStatusByValue(int idx){
			switch (idx)
			{				
				case 0:
					return NotStart;
				case 1:
					return InProcess;
				case 2:				
					return Completed;
				case 3:
					return Deffered;
				default:
					return null;
			}
		}
		
		public int getValue(){
			return this.value;
		}
		
		public String getName(){
			return this.name;
		}
	}
}
