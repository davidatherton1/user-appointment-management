package appointment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {

    private UUID id;

    @NotNull
    private Date startTime;
    @NotNull
    private Date endTime;

    @NotNull
    private String apptName;
    @NotNull
    private String apptType;
    @NotNull
    private String description;
    private UUID user;
}
