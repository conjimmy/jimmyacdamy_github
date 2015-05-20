package com.jimmy.mygodutch.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class User implements Serializable  {
	/**
	 * 
	 */
	
	 private static final long serialVersionUID = 3832626162173359411L;

	    private Long id;
	    private String name;                    // required
	    private String pwd;                    // required


	    private String email;                       // required; unique
	    private String phoneNumber;
	    private String website;

	    private Integer version;
	 
	    private Boolean enabled;
	//    private boolean accountExpired;
	//    private boolean accountLocked;
	//    private boolean credentialsExpired;

		private Set<UserMsg> userMsgs = new HashSet<UserMsg>();
		//jimmy add 
	    private Long loginCount;
	    private Long beViewedCount;
	    private Date lastLoginTime;

	    
		
	 
	    public Set<UserMsg> getUserMsgs() {
	    	
	    	
			return userMsgs;
		}

		public void setUserMsgs(Set<UserMsg> userMsgs) {
			this.userMsgs = userMsgs;
		}


	    
	    public Long getLoginCount() {
			return loginCount;
		}

		public void setLoginCount(Long loginCount) {
			this.loginCount = loginCount;
		}

		public Long getBeViewedCount() {
			return beViewedCount;
		}

		public void setBeViewedCount(Long beViewedCount) {
			this.beViewedCount = beViewedCount;
		}

		public Date getLastLoginTime() {
			return lastLoginTime;
		}

		public void setLastLoginTime(Date lastLoginTime) {
			this.lastLoginTime = lastLoginTime;
		}



		/**
	     * Default constructor - creates a new instance with no values set.
	     */
	    public User() {}

	    /**
	     * Create a new instance and set the username.
	     * @param username login name for user.
	     */
	    public User(final String username) {
	        this.name = username;
	    }

	    public User(String name, String pwd) {
	    	 this.name = name;
	    	 this.pwd = pwd;
		}

		public User(String id, String name, String pwd) {
			this.id=Long.parseLong(id);
			 this.name = name;
	    	 this.pwd = pwd;
		}

		
	    public Long getId() {
	        return id;
	    }

	
	    public String getName() {
	        return name;
	    }



	    public String getPwd() {
	        return pwd;
	    }

	 

//	    @Column(name="first_name",nullable=false,length=50)
//	    public String getFirstName() {
//	        return firstName;
//	    }
	//
//	    @Column(name="last_name",length=50)
//	    public String getLastName() {
//	        return lastName;
//	    }

	  
	    public String getEmail() {
	        return email;
	    }

	 
	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public String getWebsite() {
	        return website;
	    }

	    /**
	     * Returns the full name.
	     * @return firstName + ' ' + lastName
	     */
//	    @Transient
//	    public String getFullName() {
//	        return firstName + ' ' + lastName;
//	    }

//	    @Embedded
//	    public Address getAddress() {
//	        return address;
//	    }

//	    @ManyToMany(fetch = FetchType.EAGER) 
//	    @JoinTable(
//	            name="user_role",
//	            joinColumns = { @JoinColumn( name="user_id") },
//	            inverseJoinColumns = @JoinColumn( name="role_id")
//	    )    
//	    public Set<Role> getRoles() {
//	        return roles;
//	    }

	    /**
	     * Convert user roles to LabelValue objects for convenience.
	     * @return a list of LabelValue objects with role information
	     */
//	    @Transient
//	    public List<LabelValue> getRoleList() {
//	        List<LabelValue> userRoles = new ArrayList<LabelValue>();
	//
//	        if (this.roles != null) {
//	            for (Role role : roles) {
//	                // convert the user's roles to LabelValue Objects
//	                userRoles.add(new LabelValue(role.getName(), role.getName()));
//	            }
//	        }
	//
//	        return userRoles;
//	    }

	    /**
	     * Adds a role for the user
	     * @param role the fully instantiated role
	     */
//	    public void addRole(Role role) {
//	        getRoles().add(role);
//	    }
	    
	    /**
	     * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
	     * @return GrantedAuthority[] an array of roles.
	     */
//	    @Transient
//	    public GrantedAuthority[] getAuthorities() {
//	        return roles.toArray(new GrantedAuthority[0]);
//	    }

	
	    public Integer getVersion() {
	        return version;
	    }
	    
	
	    public Boolean isEnabled() {
	        return enabled;
	    }
	    
//	    @Column(name="account_expired")
//	    public boolean isAccountExpired() {
//	        return accountExpired;
//	    }
//	    
//	    /**
//	     * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
//	     */
//	    @Transient
//	    public boolean isAccountNonExpired() {
//	        return !isAccountExpired();
//	    }

//	    @Column(name="account_locked")
//	    public boolean isAccountLocked() {
//	        return accountLocked;
//	    }
//	    
	    /**
	     * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
	     */
//	    @Transient
//	    public boolean isAccountNonLocked() {
//	        return !isAccountLocked();
//	    }
//
//	    @Column(name="credentials_expired")
//	    public boolean isCredentialsExpired() {
//	        return credentialsExpired;
//	    }
	    
	    /**
	     * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
	     */
//	    @Transient
//	    public boolean isCredentialsNonExpired() {
//	        return !credentialsExpired;
//	    }
//	    
	    public void setId(Long id) {
	        this.id = id;
	    }
	    
	    public void setName(String username) {
	        this.name = username;
	    }

	    public void setPwd(String password) {
	        this.pwd = password;
	    }


	//
//	    public void setFirstName(String firstName) {
//	        this.firstName = firstName;
//	    }
	//
//	    public void setLastName(String lastName) {
//	        this.lastName = lastName;
//	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    public void setWebsite(String website) {
	        this.website = website;
	    }

//	    public void setAddress(Address address) {
//	        this.address = address;
//	    }
	//
//	    public void setRoles(Set<Role> roles) {
//	        this.roles = roles;
//	    }

	    public void setVersion(Integer version) {
	        this.version = version;
	    }
	    
	    public void setEnabled(Boolean enabled) {
	        this.enabled = enabled;
	    }

//	    public void setAccountExpired(boolean accountExpired) {
//	        this.accountExpired = accountExpired;
//	    }
//	    
//	    public void setAccountLocked(boolean accountLocked) {
//	        this.accountLocked = accountLocked;
//	    }
//
//	    public void setCredentialsExpired(boolean credentialsExpired) {
//	        this.credentialsExpired = credentialsExpired;
//	    }
	    
	    /**
	     * {@inheritDoc}
	     */
	    public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (!(o instanceof User)) {
	            return false;
	        }

	        final User user = (User) o;

	        return !(name != null ? !name.equals(user.getName()) : user.getName() != null);

	    }

	    /**
	     * {@inheritDoc}
	     */
	    public int hashCode() {
	        return (name != null ? name.hashCode() : 0);
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public String toString() {
	        StringBuffer sb = new StringBuffer();
	        sb.append( this.name)
	                .append(enabled);
	               // .append("accountExpired", this.accountExpired)
	         //       .append("credentialsExpired", this.credentialsExpired)
	           //     .append("accountLocked", this.accountLocked);

	        return sb.toString();
	    }
}
