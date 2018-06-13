package com.sgti.tfs.container;

public class Task {

	// attributes
	public String name;
	public int id ;
	public String resourceType;
	
	// child nodes
	public Description description;
	public StartDate startDate;
	public EndDate endDate;
	public String createDate;
	public String modifyDate;
	public LastCommentDate lastCommentDate;
	public Tags tags;
	public int numericPriority;
	public double effort;
	public double effortCompleted;
	public double effortToDo;
	public double progress;
	public double timeSpent;
	public double timeRemain;
	public PlannedStartDate plannedDate;
	public EntityType entityType;
	public Project project;
	public Owner owner;
	public LastCommentedUser lastCommentedUser;
	public LinkedTestPlan linkedTestPlan;
	public Release release;
	public Iteration iteration;
	public TeamIteration teamIteration;
	public Team team;
	public Priority priority;
	public EntityState entityState;
	public ResponsibleTeam responsibleTeam;
	public UserStory userStory;
	public CustomFields customFields;
	
	
	
	
	
	
	
}
