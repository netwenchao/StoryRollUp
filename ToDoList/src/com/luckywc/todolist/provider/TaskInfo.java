package com.luckywc.todolist.provider;

import com.luckywc.todolist.provider.EnumMgr.TaskPriority;
import com.luckywc.todolist.provider.EnumMgr.TaskStatus;

public class TaskInfo {
	public Integer Id;
	public String Name;
	public CategoryInfo Category;
	public TaskPriority Priority;
	public TaskStatus Status;
	public Integer TextColor;
	public String IconUrl;
	public Integer PerComplete;
	public String TaskDesc;
	public long StartDate;
	public long EndDate;
	public long DateAdd;
	public String Other1;
	public String Other2;
}
