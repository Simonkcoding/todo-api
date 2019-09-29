package com.crazybrother.todolist.entity;

import javax.persistence.*;

@Entity
@Table(name = "todo")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "description")
	private String description;

	@Column(name = "severity")
	private String severity;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },fetch= FetchType.EAGER)
	@JoinColumn(name = "fk_todolist_id")
	private ToDoListEntity todolist;

	public Todo(String description, String severity) {
		this.description = description;
		this.severity = severity;
	}

	public Todo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public ToDoListEntity getTodolist() {
		return todolist;
	}

	public void setTodolist(ToDoListEntity todolist) {
		this.todolist = todolist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((severity == null) ? 0 : severity.hashCode());
		result = prime * result + ((todolist == null) ? 0 : todolist.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (severity == null) {
			if (other.severity != null)
				return false;
		} else if (!severity.equals(other.severity))
			return false;
		if (todolist == null) {
			if (other.todolist != null)
				return false;
		} else if (!todolist.equals(other.todolist))
			return false;
		return true;
	}

}
