package exo.getArrays.SpringAuth.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

	
	private final ConfirmationTokenRepository confirmationRepo;
	
	public void saveConfirmationToken(ConfirmationToken token)
	{
		confirmationRepo.save(token);
	}
	
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationRepo.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationRepo.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
