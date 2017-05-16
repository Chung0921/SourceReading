package com.chung.source.reading.collections.pojo;

/**
 * Created by Chung.
 * Usage:
 * Description:
 * Create dateTime: 17/5/15
 */
public class User {

	//状态:可用
	public static final int STATUS_NORMAL = 1;
	//状态:不可用
	public static final int STATUS_UNAVAILABLE = -1;

	//id
	private Long id;

	//姓名
	private String name;

	//状态
	private Integer status;

	//得分
	private Double score;

	public Long getId() {
		return id;
	}

	public User setId( Long id ) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName( String name ) {
		this.name = name;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public User setStatus( Integer status ) {
		this.status = status;
		return this;
	}

	public Double getScore() {
		return score;
	}

	public User setScore( Double score ) {
		this.score = score;
		return this;
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;

		User user = (User) o;

		if ( id != null ? !id.equals( user.id ) : user.id != null ) return false;
		if ( name != null ? !name.equals( user.name ) : user.name != null ) return false;
		if ( status != null ? !status.equals( user.status ) : user.status != null ) return false;
		return score != null ? score.equals( user.score ) : user.score == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (score != null ? score.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", status=" + status +
				", score=" + score +
				'}';
	}
}
