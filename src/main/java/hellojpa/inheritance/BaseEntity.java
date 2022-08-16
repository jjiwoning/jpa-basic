package hellojpa.inheritance;


import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    private String createBy;
    private LocalDateTime date;

}
