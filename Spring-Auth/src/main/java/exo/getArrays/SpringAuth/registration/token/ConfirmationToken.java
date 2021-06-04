package exo.getArrays.SpringAuth.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import exo.getArrays.SpringAuth.model.myUserDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ConfirmationToken {

	@SequenceGenerator(
			name="token_sequence",
		sequenceName = "token_sequencee",
		allocationSize = 1 
	)
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "token_sequence")
	private Long id;
	
	@Column(nullable = false)
	private String token;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	private LocalDateTime expiresAt;
	private LocalDateTime confirmedAt;
	
	@ManyToOne
	@JoinColumn(
			nullable=false,
			name = "user_id")
	private myUserDetails user;
	
	
	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt,
			 myUserDetails user) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiredAt;
		this.user = user ;
	}
	
	
	
}
