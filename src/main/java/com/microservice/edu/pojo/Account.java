package com.microservice.edu.pojo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;

/**
 * ログイン認証 OK の場合に、アカウント情報がこのクラスに設定されます。
 */
public class Account implements UserDetails {
	private static final long serialVersionUID = 1L;

    private UserBaseProfilePojo userPojo;
    /** ロール情報を保持するリスト */
    private Collection<GrantedAuthority> authorities;

    public Account(UserBaseProfilePojo userPojo, Collection<GrantedAuthority> authorities){
		this.userPojo = userPojo;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.userPojo.getPasswd();
	}

	@Override
	public String getUsername() {
		return this.userPojo.getEmail();
	}

	public String getUserId() {
		return this.userPojo.getEmail();
	}

	public LocalDate getPasswordUpdDate(){
		return null;
	}

    /** アカウント有効期限切れの場合、false */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

    /** アカウントロックの場合、false */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

    /** 資格情報の期限切れの場合、false */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

    /** アカウントが無効の場合、false */
	@Override
	public boolean isEnabled() {
		return Integer.parseInt(this.userPojo.getEnable()) == 0;
	}
}
