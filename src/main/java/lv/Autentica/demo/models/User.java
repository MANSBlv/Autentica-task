package lv.Autentica.demo.models;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table
@Entity
public class User {
	/*
	 * each user has their own unique id, email, password, role
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter(value=AccessLevel.NONE)
	@Column(name="IdUser")
	private long idUser;
	
	@Column(name = "Email", unique = true)
	@Pattern (regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,10}$", message = "Invalid input for email")
	//regex pattern for typical emails
	@Size(min=5, max=30)
	//@NotNull
	private String email;
	//@Pattern (regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    //atleast 1 capital letter, 1 number and 1 speacial character (Password must be atleast 8 character)
    //@NotNull
	@Column(name="Password")
	private String password;
	
	@Column(name = "role")
    private Role role;
	
	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private Collection<AddedItems> addedItem;
	
	public User(String email,String password,Role role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
    //for the hash
    public void setPasswordHashed(String password){
      setPassword(new BCryptPasswordEncoder().encode(password));
    }

    //check if raw password matches with hashed
    public boolean checkPassword(String password){
      return new BCryptPasswordEncoder().matches(password, this.password);
    }
	
	
	
	
	
	
}
