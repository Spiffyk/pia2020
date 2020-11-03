package cz.zcu.fav.pia.jsf.service.impl;

import cz.zcu.fav.pia.jsf.domain.User;
import cz.zcu.fav.pia.jsf.repo.UserRepo;
import cz.zcu.fav.pia.jsf.service.ChangePasswordService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Objects;

@Service("changePasswordService")
@Getter
@Setter
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestScope
public class ChangePasswordServiceImpl implements ChangePasswordService {

	private final PasswordEncoder encoder;
	private final UserRepo repo;

	private String oldPassword;
	private String newPassword1;
	private String newPassword2;

	@Override
	public void changePassword(String old, String new1, String new2) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = repo.getUserByName(username);
		boolean canProceed = true;

		if (!encoder.matches(old, user.getPassword())) {
			FacesContext.getCurrentInstance().addMessage(":changePasswordForm:newPassword",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Old password is incorrect!", null));
            canProceed = false;
		}
		if (!Objects.equals(new1, new2)) {
			FacesContext.getCurrentInstance().addMessage(":changePasswordForm:newPassword",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "New passwords are not the same!", null));
			canProceed = false;
		}

		if (!canProceed) {
		    return;
        }

		repo.changeUserPassword(username, new1);
	}

	public void changePassword() {
		this.changePassword(oldPassword, newPassword1, newPassword2);
	}

}
