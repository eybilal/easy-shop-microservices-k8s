package io.coodle.authserver.model.entity;

import io.coodle.authserver.model.pojo.BaseEntity;
import io.coodle.authserver.model.pojo.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "`user`")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SuperBuilder
public class AppUser extends BaseEntity {
    @Column(name = "username", unique = true)
    private String username;

    // @Getter(onMethod = @__( @JsonIgnore)) --> works also
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private UserStatus status;

    // By default is LAZY, means if I load an object user from database,
    // JPA will not load automatically the roles of the user.
    // It will be loaded when we call a method getRoles()
    // We choose EAGER, to load also roles automatically when we load an object user
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles;

//    @OneToMany(
//        mappedBy = "user",
//        cascade = CascadeType.ALL,
//        orphanRemoval = true
//    )
//    private Collection<ShoppingList> shoppingLists;

    // TODO
    // private Address shippingAddress;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    //private Collection<CreditCard> creditCards;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    //private Collection<ElectronicBankTransfer> bankAccounts;

    public void addRole(AppRole role) {
        if(this.roles == null) {
            this.roles = new ArrayList<>();
        }

        this.roles.add(role);
    }
}
