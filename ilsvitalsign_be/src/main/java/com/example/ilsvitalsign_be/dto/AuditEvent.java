package com.example.ilsvitalsign_be.dto;

import java.util.Date;

public class AuditEvent {
	private Date time = new Date();
	private String name;
	private String description;
	private String category;
	private long count;
	private String level = "info";

	public AuditEvent() {

	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public AuditEvent(String category, String description) {
		this.category = category;
		this.description = description;
	}
}
