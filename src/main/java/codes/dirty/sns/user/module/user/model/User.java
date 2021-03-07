package codes.dirty.sns.user.module.user.model;

import codes.dirty.sns.user.common.model.BaseEntity;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Builder
    public User(@NonNull final Instant createdAt,
                @Nullable final Instant updatedAt,
                @NonNull final String email) {
        super();
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
        this.email = email;
    }
}
