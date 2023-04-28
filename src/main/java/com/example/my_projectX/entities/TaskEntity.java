package com.example.my_projectX.entities;

import java.util.List;

public class TaskEntity {
	public int id;
	public String title;
	public String description;
	public String deadline;
    public Boolean compleated;
	public List<NoteEntity> notes;
}
