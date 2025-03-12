package com.qbe.persistence;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "TBL_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class User implements Serializable{

	private static final long serialVersionUID = 34131213L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name = "USER_ID", nullable = false)
	private String userID;

	@Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
	
	@Column(name = "LAST_NAME")
    private String lastName;
	
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ACTIVE")
	private String activeIndicator = "Y";

	@Column(name= "CREATE_TSTAMP", nullable = false)
	private LocalDate createTimestamp;

	@Column(name = "CREATE_USER_ID", nullable = false)
	private String createUserID;

	@Column(name= "DISABLE_TSTAMP")
	private LocalDate disableTimestamp;

	@Column(name= "UPDATE_TSTAMP")
	private LocalDate updateTimestamp;

	@Column(name = "UPDATE_USER_ID")
	private String updateUserID;

	@Transient
	public boolean isActive(){return "Y".equals(activeIndicator);}

	public void setActive(boolean active){this.activeIndicator = active ? "Y": "N";}
}
