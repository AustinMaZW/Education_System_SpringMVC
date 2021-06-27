package sg.edu.iss.caps.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import sg.edu.iss.caps.model.User;

public class UserDetailsImpl implements UserDetails{

	private String username;
	private String password;
	private boolean active;
	private String firstName;
	private int id;
	private Collection<GrantedAuthority> authorities = new HashSet<>();
	
	public UserDetailsImpl(User user, String role) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		//hard coding this for now, but can add into attribute later
		this.active = true;
		this.firstName = user.getFirstName();
		this.id = user.getId();
		this.authorities.add(new SimpleGrantedAuthority(role));
		//assuming only one role per account, so hard coding it for now..
//		this.authorities = Arrays.stream(user.getRoles())
//				.map(SimpleGrantedAuthority::new)
//				.collect(Collectors.toList());
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public int getId() {
		return id;
	}
}
