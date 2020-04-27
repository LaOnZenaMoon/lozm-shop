package lozm.entity;

import lozm.code.UsersType;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "CREATED_DT")
    private LocalDateTime createdDt = LocalDateTime.now();

    @Column(name = "MODIFIED_DT")
    private LocalDateTime modifiedDt;

    @Column(name = "CREATED_BY")
    private String createdBy = UsersType.API_SYSTEM.toString();

    @Column(name = "MODIFY_BY")
    private String modifiedBy;

    @Column(name = "FLAG")
    private int flag = 1;

    public void setBaseEntity(String modifiedBy, int flag) {
        this.modifiedDt = LocalDateTime.now();
        this.modifiedBy = StringUtils.isEmpty(modifiedBy) ? UsersType.API_SYSTEM.toString() : modifiedBy;
        if(!StringUtils.isEmpty(flag)) this.flag = flag;
    }

}