package com.devsuperior.movieflix.projection;

public interface UserDetailsProjection {
	String getUsername();
	String getPassword();
	Long getRoleId();
	String getAuthority();
}
