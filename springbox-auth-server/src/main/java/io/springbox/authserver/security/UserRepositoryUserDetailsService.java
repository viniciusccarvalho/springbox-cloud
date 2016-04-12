package io.springbox.authserver.security;

import java.util.Collection;

import io.springbox.authserver.domain.User;
import io.springbox.authserver.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Vinicius Carvalho
 */
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;

	@Autowired
	public UserRepositoryUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		}
		return new UserRepositoryUserDetails(user);
	}


	private final static class UserRepositoryUserDetails extends User implements UserDetails {

		private UserRepositoryUserDetails(User user) {
			super(user);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return AuthorityUtils.createAuthorityList("ROLE_USER");
		}

		@Override
		public String getUsername() {
			return getEmail();
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

		private static final long serialVersionUID = 5639683223516504866L;
	}
}
