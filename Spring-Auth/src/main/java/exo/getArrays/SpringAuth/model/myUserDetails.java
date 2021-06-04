package exo.getArrays.SpringAuth.model;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class myUserDetails implements UserDetails {

	@Id
	@SequenceGenerator(
			name="student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1 
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator="student_sequence"
			)
	private Long id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	@Enumerated(EnumType.STRING)
	private AppUserRole userRole;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean locked = false ;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled = false ;
	
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority authority = 
				new SimpleGrantedAuthority(userRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	public myUserDetails(String firstName, String lastName, String password,
			String email, AppUserRole userRole ) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.userRole = userRole;
	
	}

}
