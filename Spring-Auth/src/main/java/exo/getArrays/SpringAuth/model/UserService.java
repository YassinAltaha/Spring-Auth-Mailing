package exo.getArrays.SpringAuth.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import exo.getArrays.SpringAuth.registration.token.ConfirmationToken;
import exo.getArrays.SpringAuth.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

	
	private final UserRepository userRepo;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email)
				.orElseThrow(
						() -> new UsernameNotFoundException("user with email " + email + " not found")
						);
	}
	
	public String singUpUser(myUserDetails user) {
		
		
		boolean userExisits = 
				userRepo.findByEmail(user.getEmail())
				.isPresent();
		
		if(userExisits) {
			throw new IllegalStateException("Email Already Taken");
		}
		
		String encodedPassword = 
				bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		String token = UUID.randomUUID().toString();
		//TODO: Send Confirmation Token
		ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				user
				);
		
		
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		//TODO: SEND EMAIL
		
		return token;
	}
	
    public int enableUser(String email) {
        return userRepo.enableUser(email);
    }

}
