// package com.todo.regproc;

// import java.util.Collection;
// import java.util.Collections;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import jakarta.persistence.*;
// import lombok.Data;
// import lombok.EqualsAndHashCode;
// import lombok.NoArgsConstructor;


// @Data
// @EqualsAndHashCode
// @NoArgsConstructor
// @Entity
// public class AppUser implements UserDetails {

//     @Id
//     @SequenceGenerator(name = "au_sequence", sequenceName = "au_sequence", allocationSize = 1)
//     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "au_sequence")
//     private Long id; 

//     private String email;

//     private String password;

//     @Enumerated(EnumType.STRING)
//     private AppUserRole appUserRole;

//     private Boolean locked;

//     private Boolean enabled;
    

//     public AppUser(String email, String password, AppUserRole appUserRole, Boolean locked, Boolean enabled) {
//         this.email = email;
//         this.password = password;
//         this.appUserRole = appUserRole;
//         this.locked = locked;
//         this.enabled = enabled;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
//             return Collections.singletonList(authority);
//     }

//     @Override
//     public String getPassword() {
//         return password;
//     }

//     @Override
//     public String getUsername() {
//         return email;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return !locked;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return enabled;
//     }
    
// }
