package io.springbox.authserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents a user in our system.
 *
 * <p>
 * In a real system use {@link PasswordEncoder} to ensure the password is secured
 * properly. This demonstration does not address this due to time restrictions.
 * </p>
 *
 * @author Rob Winch
 */
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;

	@NotEmpty(message = "First name is required.")
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotEmpty(message = "Last name is required.")
	@Column(name = "LAST_NAME")
	private String lastName;

	@Email(message = "Please provide a valid email address.")
	@NotEmpty(message = "Email is required.")
	@Column(unique=true, nullable = false, name = "EMAIL")
	private String email;

	@NotEmpty(message = "Password is required.")
	@Column(name = "PASSWORD")
	private String password;

	public User() {}

	public User(User user) {
		this.id = user.id;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.password = user.password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}